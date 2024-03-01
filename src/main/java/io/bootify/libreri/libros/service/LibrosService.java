package io.bootify.libreri.libros.service;

import io.bootify.libreri.autor.domain.Autor;
import io.bootify.libreri.autor.repos.AutorRepository;
import io.bootify.libreri.editorial.domain.Editorial;
import io.bootify.libreri.editorial.repos.EditorialRepository;
import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.genero.domain.Genero;
import io.bootify.libreri.genero.repos.GeneroRepository;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.model.LibrosDTO;
import io.bootify.libreri.libros.repos.LibrosRepository;
<<<<<<< HEAD
import io.bootify.libreri.util.NotFoundException;
=======
import io.bootify.libreri.errors.NotFoundException;
>>>>>>> Joaquin-System
import io.bootify.libreri.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class LibrosService {

    private final LibrosRepository librosRepository;
    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;
    private final EditorialRepository editorialRepository;
    private final EjemplarRepository ejemplarRepository;

    public LibrosService(final LibrosRepository librosRepository,
            final AutorRepository autorRepository, final GeneroRepository generoRepository,
            final EditorialRepository editorialRepository,
            final EjemplarRepository ejemplarRepository) {
        this.librosRepository = librosRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.editorialRepository = editorialRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    public List<LibrosDTO> findAll() {
        final List<Libros> libroses = librosRepository.findAll(Sort.by("isbn"));
        return libroses.stream()
                .map(libros -> mapToDTO(libros, new LibrosDTO()))
                .toList();
    }

    public LibrosDTO get(final Integer isbn) {
        return librosRepository.findById(isbn)
                .map(libros -> mapToDTO(libros, new LibrosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final LibrosDTO librosDTO) {
        final Libros libros = new Libros();
        mapToEntity(librosDTO, libros);
        return librosRepository.save(libros).getIsbn();
    }

    public void update(final Integer isbn, final LibrosDTO librosDTO) {
        final Libros libros = librosRepository.findById(isbn)
                .orElseThrow(NotFoundException::new);
        mapToEntity(librosDTO, libros);
        librosRepository.save(libros);
    }

    public void delete(final Integer isbn) {
        final Libros libros = librosRepository.findById(isbn)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        generoRepository.findAllByLibroGeneroLibroses(libros)
                .forEach(genero -> genero.getLibroGeneroLibroses().remove(libros));
        editorialRepository.findAllByLibroEditorialLibroses(libros)
                .forEach(editorial -> editorial.getLibroEditorialLibroses().remove(libros));
        librosRepository.delete(libros);
    }

    private LibrosDTO mapToDTO(final Libros libros, final LibrosDTO librosDTO) {
        librosDTO.setIsbn(libros.getIsbn());
        librosDTO.setTitulo(libros.getTitulo());
        librosDTO.setLibroAutorAutors(libros.getLibroAutorAutors().stream()
                .map(autor -> autor.getIdAutor())
                .toList());
        return librosDTO;
    }

    private Libros mapToEntity(final LibrosDTO librosDTO, final Libros libros) {
        libros.setTitulo(librosDTO.getTitulo());
        final List<Autor> libroAutorAutors = autorRepository.findAllById(
                librosDTO.getLibroAutorAutors() == null ? Collections.emptyList() : librosDTO.getLibroAutorAutors());
        if (libroAutorAutors.size() != (librosDTO.getLibroAutorAutors() == null ? 0 : librosDTO.getLibroAutorAutors().size())) {
            throw new NotFoundException("one of libroAutorAutors not found");
        }
        libros.setLibroAutorAutors(libroAutorAutors.stream().collect(Collectors.toSet()));
        return libros;
    }

    public String getReferencedWarning(final Integer isbn) {
        final Libros libros = librosRepository.findById(isbn)
                .orElseThrow(NotFoundException::new);
        final Genero libroGeneroLibrosesGenero = generoRepository.findFirstByLibroGeneroLibroses(libros);
        if (libroGeneroLibrosesGenero != null) {
            return WebUtils.getMessage("libros.genero.libroGeneroLibroses.referenced", libroGeneroLibrosesGenero.getIdGenero());
        }
        final Editorial libroEditorialLibrosesEditorial = editorialRepository.findFirstByLibroEditorialLibroses(libros);
        if (libroEditorialLibrosesEditorial != null) {
            return WebUtils.getMessage("libros.editorial.libroEditorialLibroses.referenced", libroEditorialLibrosesEditorial.getIdEditorial());
        }
        final Ejemplar libroEjemplar = ejemplarRepository.findFirstByLibro(libros);
        if (libroEjemplar != null) {
            return WebUtils.getMessage("libros.ejemplar.libro.referenced", libroEjemplar.getIdEjemplar());
        }
        return null;
    }

}
