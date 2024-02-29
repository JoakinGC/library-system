package io.bootify.libreri.socio.service;

import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.socio.domain.Socio;
import io.bootify.libreri.socio.model.SocioDTO;
import io.bootify.libreri.socio.repos.SocioRepository;
import io.bootify.libreri.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SocioService {

    private final SocioRepository socioRepository;
    private final PrestamoRepository prestamoRepository;

    public SocioService(final SocioRepository socioRepository,
            final PrestamoRepository prestamoRepository) {
        this.socioRepository = socioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<SocioDTO> findAll() {
        final List<Socio> socios = socioRepository.findAll(Sort.by("idSocio"));
        return socios.stream()
                .map(socio -> mapToDTO(socio, new SocioDTO()))
                .toList();
    }

    public SocioDTO get(final Integer idSocio) {
        return socioRepository.findById(idSocio)
                .map(socio -> mapToDTO(socio, new SocioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final SocioDTO socioDTO) {
        final Socio socio = new Socio();
        mapToEntity(socioDTO, socio);
        return socioRepository.save(socio).getIdSocio();
    }

    public void update(final Integer idSocio, final SocioDTO socioDTO) {
        final Socio socio = socioRepository.findById(idSocio)
                .orElseThrow(NotFoundException::new);
        mapToEntity(socioDTO, socio);
        socioRepository.save(socio);
    }

    public void delete(final Integer idSocio) {
        socioRepository.deleteById(idSocio);
    }


    private SocioDTO mapToDTO(final Socio socio, final SocioDTO socioDTO) {
        socioDTO.setIdSocio(socio.getIdSocio());
        socioDTO.setNombre(socio.getNombre());
        socioDTO.setApellido(socio.getApellido());
        socioDTO.setTelefono(socio.getTelefono());
        socioDTO.setDni(socio.getDni());
        socioDTO.setDireccion(socio.getDireccion());
        socioDTO.setMulta(socio.getMulta());
        socioDTO.setMultaTotal(socio.getMultaTotal());
        socioDTO.setPrestamo(socio.getPrestamo() == null ? null : socio.getPrestamo().getIdPrestamo());
        return socioDTO;
    }

    private Socio mapToEntity(final SocioDTO socioDTO, final Socio socio) {
        socio.setNombre(socioDTO.getNombre());
        socio.setApellido(socioDTO.getApellido());
        socio.setTelefono(socioDTO.getTelefono());
        socio.setDni(socioDTO.getDni());
        socio.setDireccion(socioDTO.getDireccion());
        socio.setMulta(socioDTO.getMulta());
        socio.setMultaTotal(socioDTO.getMultaTotal());
        final Prestamo prestamo = socioDTO.getPrestamo() == null ? null : prestamoRepository.findById(socioDTO.getPrestamo())
                .orElseThrow(() -> new NotFoundException("prestamo not found"));
        socio.setPrestamo(prestamo);
        return socio;
    }

}
