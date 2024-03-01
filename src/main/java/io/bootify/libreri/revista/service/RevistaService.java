package io.bootify.libreri.revista.service;

import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.genero.domain.Genero;
import io.bootify.libreri.genero.repos.GeneroRepository;
import io.bootify.libreri.revista.domain.Revista;
import io.bootify.libreri.revista.model.RevistaDTO;
import io.bootify.libreri.revista.repos.RevistaRepository;
import io.bootify.libreri.errors.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RevistaService {

    private final RevistaRepository revistaRepository;
    private final EjemplarRepository ejemplarRepository;
    private final GeneroRepository generoRepository;

    public RevistaService(final RevistaRepository revistaRepository,
                          final EjemplarRepository ejemplarRepository, final GeneroRepository generoRepository) {
        this.revistaRepository = revistaRepository;
        this.ejemplarRepository = ejemplarRepository;
        this.generoRepository = generoRepository;
    }

    public List<RevistaDTO> findAll() {
        final List<Revista> revistas = revistaRepository.findAll(Sort.by("idRevista"));
        return revistas.stream()
                .map(revista -> mapToDTO(revista, new RevistaDTO()))
                .toList();
    }

    public RevistaDTO get(final Integer idRevista) {
        return revistaRepository.findById(idRevista)
                .map(revista -> mapToDTO(revista, new RevistaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RevistaDTO revistaDTO) {
        final Revista revista = new Revista();
        mapToEntity(revistaDTO, revista);
        return revistaRepository.save(revista).getIdRevista();
    }

    public void update(final Integer idRevista, final RevistaDTO revistaDTO) {
        final Revista revista = revistaRepository.findById(idRevista)
                .orElseThrow(NotFoundException::new);
        mapToEntity(revistaDTO, revista);
        revistaRepository.save(revista);
    }

    public void delete(final Integer idRevista) {
        final  Revista revista = revistaRepository.findById(idRevista)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        generoRepository.findAllByGeneroRevistaRevistas(revista)
                .forEach(genero -> genero.getGeneroRevistaRevistas().remove(revista));
        revistaRepository.delete(revista);
    }

    private RevistaDTO mapToDTO(final Revista revista, final RevistaDTO revistaDTO) {
        revistaDTO.setIdRevista(revista.getIdRevista());
        revistaDTO.setTitulo(revista.getTitulo());
        return revistaDTO;
    }

    private Revista mapToEntity(final RevistaDTO revistaDTO, final Revista revista) {
        revista.setTitulo(revistaDTO.getTitulo());
        return revista;
    }

    public String getReferencedWarning(final Integer idRevista) {
        final Revista revista = revistaRepository.findById(idRevista)
                .orElseThrow(NotFoundException::new);
        final Genero generoRevistaRevistasGenero = generoRepository.findFirstByGeneroRevistaRevistas(revista);
        if (generoRevistaRevistasGenero != null) {
            return WebUtils.getMessage("revista.genero.generoRevistaRevistas.referenced", generoRevistaRevistasGenero.getIdGenero());
        }

        return null;
    }

}
