package io.bootify.libreri.ejemplar.service;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.model.EjemplarDTO;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.revista.domain.Revista;
import io.bootify.libreri.revista.repos.RevistaRepository;
import io.bootify.libreri.errors.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final LibrosRepository librosRepository;
    private final RevistaRepository revistaRepository;
    private final PrestamoRepository prestamoRepository;

    public EjemplarService(final EjemplarRepository ejemplarRepository,
                           final LibrosRepository librosRepository, final RevistaRepository revistaRepository,
                           final PrestamoRepository prestamoRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.librosRepository = librosRepository;
        this.revistaRepository = revistaRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<EjemplarDTO> findAll() {
        final List<Ejemplar> ejemplars = ejemplarRepository.findAll(Sort.by("idEjemplar"));
        return ejemplars.stream()
                .map(ejemplar -> mapToDTO(ejemplar, new EjemplarDTO()))
                .toList();
    }

    public EjemplarDTO get(final Integer idEjemplar) {
        return ejemplarRepository.findById(idEjemplar)
                .map(ejemplar -> mapToDTO(ejemplar, new EjemplarDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EjemplarDTO ejemplarDTO) {
        final Ejemplar ejemplar = new Ejemplar();
        mapToEntity(ejemplarDTO, ejemplar);
        return ejemplarRepository.save(ejemplar).getIdEjemplar();
    }

    public void update(final Integer idEjemplar, final EjemplarDTO ejemplarDTO) {
        final Ejemplar ejemplar = ejemplarRepository.findById(idEjemplar)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ejemplarDTO, ejemplar);
        ejemplarRepository.save(ejemplar);
    }

    public void delete(final Integer idEjemplar) {
        ejemplarRepository.deleteById(idEjemplar);
    }

    public List<Libros> findAllLibrosWithEjemplar() {
        return ejemplarRepository.findAllLibrosWithEjemplar();
    }

    public List<Revista> findAllRevistasWithEjemplar() {
        return ejemplarRepository.findAllRevistasWithEjemplar();
    }

    private EjemplarDTO mapToDTO(final Ejemplar ejemplar, final EjemplarDTO ejemplarDTO) {
        ejemplarDTO.setIdEjemplar(ejemplar.getIdEjemplar());
        ejemplarDTO.setLibro(ejemplar.getLibro() == null ? null : ejemplar.getLibro().getIsbn());
        ejemplarDTO.setRevista(ejemplar.getRevista() == null ? null : ejemplar.getRevista().getIdRevista());
        return ejemplarDTO;
    }

    private Ejemplar mapToEntity(final EjemplarDTO ejemplarDTO, final Ejemplar ejemplar) {
        final Libros libro = ejemplarDTO.getLibro() == null ? null : librosRepository.findById(ejemplarDTO.getLibro())
                .orElseThrow(() -> new NotFoundException("libro not found"));
        ejemplar.setLibro(libro);
        final Revista revista = ejemplarDTO.getRevista() == null ? null : revistaRepository.findById(ejemplarDTO.getRevista())
                .orElseThrow(() -> new NotFoundException("revista not found"));
        ejemplar.setRevista(revista);
        return ejemplar;
    }

    public String getReferencedWarning(final Integer idEjemplar) {
        final Ejemplar ejemplar = ejemplarRepository.findById(idEjemplar)
                .orElseThrow(NotFoundException::new);

        final Prestamo ejemplarPrestamo = prestamoRepository.findFirstByEjemplar(ejemplar);
        if (ejemplarPrestamo != null) {
            return WebUtils.getMessage("ejemplar.prestamo.ejemplar.referenced", ejemplarPrestamo.getIdPrestamo());
        }
        return null;
    }

}
