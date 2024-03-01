package io.bootify.libreri.editorial.service;

import io.bootify.libreri.editorial.domain.Editorial;
import io.bootify.libreri.editorial.model.EditorialDTO;
import io.bootify.libreri.editorial.repos.EditorialRepository;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.errors.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EditorialService {

    private final EditorialRepository editorialRepository;
    private final LibrosRepository librosRepository;

    public EditorialService(final EditorialRepository editorialRepository,
            final LibrosRepository librosRepository) {
        this.editorialRepository = editorialRepository;
        this.librosRepository = librosRepository;
    }

    public List<EditorialDTO> findAll() {
        final List<Editorial> editorials = editorialRepository.findAll(Sort.by("idEditorial"));
        return editorials.stream()
                .map(editorial -> mapToDTO(editorial, new EditorialDTO()))
                .toList();
    }

    public EditorialDTO get(final Integer idEditorial) {
        return editorialRepository.findById(idEditorial)
                .map(editorial -> mapToDTO(editorial, new EditorialDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EditorialDTO editorialDTO) {
        final Editorial editorial = new Editorial();
        mapToEntity(editorialDTO, editorial);
        return editorialRepository.save(editorial).getIdEditorial();
    }

    public void update(final Integer idEditorial, final EditorialDTO editorialDTO) {
        final Editorial editorial = editorialRepository.findById(idEditorial)
                .orElseThrow(NotFoundException::new);
        mapToEntity(editorialDTO, editorial);
        editorialRepository.save(editorial);
    }

    public void delete(final Integer idEditorial) {
        editorialRepository.deleteById(idEditorial);
    }

    private EditorialDTO mapToDTO(final Editorial editorial, final EditorialDTO editorialDTO) {
        editorialDTO.setIdEditorial(editorial.getIdEditorial());
        editorialDTO.setNombre(editorial.getNombre());
        editorialDTO.setLibroEditorialLibroses(editorial.getLibroEditorialLibroses().stream()
                .map(libros -> libros.getIsbn())
                .toList());
        return editorialDTO;
    }

    private Editorial mapToEntity(final EditorialDTO editorialDTO, final Editorial editorial) {
        editorial.setNombre(editorialDTO.getNombre());
        final List<Libros> libroEditorialLibroses = librosRepository.findAllById(
                editorialDTO.getLibroEditorialLibroses() == null ? Collections.emptyList() : editorialDTO.getLibroEditorialLibroses());
        if (libroEditorialLibroses.size() != (editorialDTO.getLibroEditorialLibroses() == null ? 0 : editorialDTO.getLibroEditorialLibroses().size())) {
            throw new NotFoundException("one of libroEditorialLibroses not found");
        }
        editorial.setLibroEditorialLibroses(libroEditorialLibroses.stream().collect(Collectors.toSet()));
        return editorial;
    }

}
