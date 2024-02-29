package io.bootify.libreri.autor.service;

import io.bootify.libreri.autor.domain.Autor;
import io.bootify.libreri.autor.model.AutorDTO;
import io.bootify.libreri.autor.repos.AutorRepository;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.util.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AutorService {

    private final AutorRepository autorRepository;
    private final LibrosRepository librosRepository;

    public AutorService(final AutorRepository autorRepository,
            final LibrosRepository librosRepository) {
        this.autorRepository = autorRepository;
        this.librosRepository = librosRepository;
    }

    public List<AutorDTO> findAll() {
        final List<Autor> autors = autorRepository.findAll(Sort.by("idAutor"));
        return autors.stream()
                .map(autor -> mapToDTO(autor, new AutorDTO()))
                .toList();
    }

    public AutorDTO get(final Integer idAutor) {
        return autorRepository.findById(idAutor)
                .map(autor -> mapToDTO(autor, new AutorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final AutorDTO autorDTO) {
        final Autor autor = new Autor();
        mapToEntity(autorDTO, autor);
        return autorRepository.save(autor).getIdAutor();
    }

    public void update(final Integer idAutor, final AutorDTO autorDTO) {
        final Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(NotFoundException::new);
        mapToEntity(autorDTO, autor);
        autorRepository.save(autor);
    }

    public void delete(final Integer idAutor) {
        final Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        librosRepository.findAllByLibroAutorAutors(autor)
                .forEach(libros -> libros.getLibroAutorAutors().remove(autor));
        autorRepository.delete(autor);
    }

    private AutorDTO mapToDTO(final Autor autor, final AutorDTO autorDTO) {
        autorDTO.setIdAutor(autor.getIdAutor());
        autorDTO.setNombre(autor.getNombre());
        autorDTO.setApellido(autor.getApellido());
        return autorDTO;
    }

    private Autor mapToEntity(final AutorDTO autorDTO, final Autor autor) {
        autor.setNombre(autorDTO.getNombre());
        autor.setApellido(autorDTO.getApellido());
        return autor;
    }

    public String getReferencedWarning(final Integer idAutor) {
        final Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(NotFoundException::new);
        final Libros libroAutorAutorsLibros = librosRepository.findFirstByLibroAutorAutors(autor);
        if (libroAutorAutorsLibros != null) {
            return WebUtils.getMessage("autor.libros.libroAutorAutors.referenced", libroAutorAutorsLibros.getIsbn());
        }
        return null;
    }

}
