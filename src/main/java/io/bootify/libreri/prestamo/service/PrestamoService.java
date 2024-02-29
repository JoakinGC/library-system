package io.bootify.libreri.prestamo.service;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.model.PrestamoDTO;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.socio.domain.Socio;
import io.bootify.libreri.socio.repos.SocioRepository;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import io.bootify.libreri.util.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;
    private final UsuarioRepository usuarioRepository;
    private final SocioRepository socioRepository;

    public PrestamoService(final PrestamoRepository prestamoRepository,
            final EjemplarRepository ejemplarRepository, final UsuarioRepository usuarioRepository,
            final SocioRepository socioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
        this.usuarioRepository = usuarioRepository;
        this.socioRepository = socioRepository;
    }

    public List<PrestamoDTO> findAll() {
        final List<Prestamo> prestamoes = prestamoRepository.findAll(Sort.by("idPrestamo"));
        return prestamoes.stream()
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .toList();
    }

    public PrestamoDTO get(final Integer idPrestamo) {
        return prestamoRepository.findById(idPrestamo)
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = new Prestamo();
        mapToEntity(prestamoDTO, prestamo);
        return prestamoRepository.save(prestamo).getIdPrestamo();
    }

    public void update(final Integer idPrestamo, final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(NotFoundException::new);
        mapToEntity(prestamoDTO, prestamo);
        prestamoRepository.save(prestamo);
    }

    public void delete(final Integer idPrestamo) {
        prestamoRepository.deleteById(idPrestamo);
    }

    private PrestamoDTO mapToDTO(final Prestamo prestamo, final PrestamoDTO prestamoDTO) {
        prestamoDTO.setIdPrestamo(prestamo.getIdPrestamo());
        prestamoDTO.setTipo(prestamo.getTipo());
        prestamoDTO.setFechaPrestamo(prestamo.getFechaPrestamo());
        prestamoDTO.setFechaFin(prestamo.getFechaFin());
        prestamoDTO.setFechaEntrega(prestamo.getFechaEntrega());
        prestamoDTO.setEjemplar(prestamo.getEjemplar() == null ? null : prestamo.getEjemplar().getIdEjemplar());
        prestamoDTO.setEmple(prestamo.getEmple() == null ? null : prestamo.getEmple().getIdUser());
        return prestamoDTO;
    }

    private Prestamo mapToEntity(final PrestamoDTO prestamoDTO, final Prestamo prestamo) {
        prestamo.setTipo(prestamoDTO.getTipo());
        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaFin(prestamoDTO.getFechaFin());
        prestamo.setFechaEntrega(prestamoDTO.getFechaEntrega());
        final Ejemplar ejemplar = prestamoDTO.getEjemplar() == null ? null : ejemplarRepository.findById(prestamoDTO.getEjemplar())
                .orElseThrow(() -> new NotFoundException("ejemplar not found"));
        prestamo.setEjemplar(ejemplar);
        final Usuario emple = prestamoDTO.getEmple() == null ? null : usuarioRepository.findById(prestamoDTO.getEmple())
                .orElseThrow(() -> new NotFoundException("emple not found"));
        prestamo.setEmple(emple);
        return prestamo;
    }

    public String getReferencedWarning(final Integer idPrestamo) {
        final Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(NotFoundException::new);
        final Socio prestamoSocio = socioRepository.findFirstByPrestamo(prestamo);
        if (prestamoSocio != null) {
            return WebUtils.getMessage("prestamo.socio.prestamo.referenced", prestamoSocio.getIdSocio());
        }
        return null;
    }

}
