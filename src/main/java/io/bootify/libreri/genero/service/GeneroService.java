package io.bootify.libreri.genero.service;

import io.bootify.libreri.genero.domain.Genero;
import io.bootify.libreri.genero.model.GeneroDTO;
import io.bootify.libreri.genero.repos.GeneroRepository;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.revista.domain.Revista;
import io.bootify.libreri.revista.repos.RevistaRepository;
<<<<<<< HEAD
import io.bootify.libreri.util.NotFoundException;
=======
import io.bootify.libreri.errors.NotFoundException;
>>>>>>> Joaquin-System
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class GeneroService {

    private final GeneroRepository generoRepository;
    private final LibrosRepository librosRepository;
    private final RevistaRepository revistaRepository;

    public GeneroService(final GeneroRepository generoRepository,
            final LibrosRepository librosRepository, final RevistaRepository revistaRepository) {
        this.generoRepository = generoRepository;
        this.librosRepository = librosRepository;
        this.revistaRepository = revistaRepository;
    }

    public List<GeneroDTO> findAll() {
        final List<Genero> generoes = generoRepository.findAll(Sort.by("idGenero"));
        return generoes.stream()
                .map(genero -> mapToDTO(genero, new GeneroDTO()))
                .toList();
    }

    public GeneroDTO get(final Integer idGenero) {
        return generoRepository.findById(idGenero)
                .map(genero -> mapToDTO(genero, new GeneroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final GeneroDTO generoDTO) {
        final Genero genero = new Genero();
        mapToEntity(generoDTO, genero);
        return generoRepository.save(genero).getIdGenero();
    }

    public void update(final Integer idGenero, final GeneroDTO generoDTO) {
        final Genero genero = generoRepository.findById(idGenero)
                .orElseThrow(NotFoundException::new);
        mapToEntity(generoDTO, genero);
        generoRepository.save(genero);
    }

    public void delete(final Integer idGenero) {
        generoRepository.deleteById(idGenero);
    }

    private GeneroDTO mapToDTO(final Genero genero, final GeneroDTO generoDTO) {
        generoDTO.setIdGenero(genero.getIdGenero());
        generoDTO.setNombre(genero.getNombre());
        generoDTO.setLibroGeneroLibroses(genero.getLibroGeneroLibroses().stream()
                .map(libros -> libros.getIsbn())
                .toList());
        generoDTO.setGeneroRevistaRevistas(genero.getGeneroRevistaRevistas().stream()
                .map(revista -> revista.getIdRevista())
                .toList());
        return generoDTO;
    }

    private Genero mapToEntity(final GeneroDTO generoDTO, final Genero genero) {
        genero.setNombre(generoDTO.getNombre());
        final List<Libros> libroGeneroLibroses = librosRepository.findAllById(
                generoDTO.getLibroGeneroLibroses() == null ? Collections.emptyList() : generoDTO.getLibroGeneroLibroses());
        if (libroGeneroLibroses.size() != (generoDTO.getLibroGeneroLibroses() == null ? 0 : generoDTO.getLibroGeneroLibroses().size())) {
            throw new NotFoundException("one of libroGeneroLibroses not found");
        }
        genero.setLibroGeneroLibroses(libroGeneroLibroses.stream().collect(Collectors.toSet()));
        final List<Revista> generoRevistaRevistas = revistaRepository.findAllById(
                generoDTO.getGeneroRevistaRevistas() == null ? Collections.emptyList() : generoDTO.getGeneroRevistaRevistas());
        if (generoRevistaRevistas.size() != (generoDTO.getGeneroRevistaRevistas() == null ? 0 : generoDTO.getGeneroRevistaRevistas().size())) {
            throw new NotFoundException("one of generoRevistaRevistas not found");
        }
        genero.setGeneroRevistaRevistas(generoRevistaRevistas.stream().collect(Collectors.toSet()));
        return genero;
    }

}
