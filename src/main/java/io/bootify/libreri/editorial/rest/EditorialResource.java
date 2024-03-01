package io.bootify.libreri.editorial.rest;

import io.bootify.libreri.editorial.model.EditorialDTO;
import io.bootify.libreri.editorial.service.EditorialService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/editorials", produces = MediaType.APPLICATION_JSON_VALUE)
public class EditorialResource {

    private final EditorialService editorialService;

    public EditorialResource(final EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public ResponseEntity<List<EditorialDTO>> getAllEditorials() {
        return ResponseEntity.ok(editorialService.findAll());
    }

    @GetMapping("/{idEditorial}")
    public ResponseEntity<EditorialDTO> getEditorial(
            @PathVariable(name = "idEditorial") final Integer idEditorial) {
        return ResponseEntity.ok(editorialService.get(idEditorial));
    }

    @PostMapping
    public ResponseEntity<Integer> createEditorial(
            @RequestBody @Valid final EditorialDTO editorialDTO) {
        final Integer createdIdEditorial = editorialService.create(editorialDTO);
        return new ResponseEntity<>(createdIdEditorial, HttpStatus.CREATED);
    }

    @PutMapping("/{idEditorial}")
    public ResponseEntity<Integer> updateEditorial(
            @PathVariable(name = "idEditorial") final Integer idEditorial,
            @RequestBody @Valid final EditorialDTO editorialDTO) {
        editorialService.update(idEditorial, editorialDTO);
        return ResponseEntity.ok(idEditorial);
    }

    @DeleteMapping("/{idEditorial}")
    public ResponseEntity<Void> deleteEditorial(
            @PathVariable(name = "idEditorial") final Integer idEditorial) {
        editorialService.delete(idEditorial);
        return ResponseEntity.noContent().build();
    }

}
