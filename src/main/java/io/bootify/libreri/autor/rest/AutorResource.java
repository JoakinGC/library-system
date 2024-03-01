package io.bootify.libreri.autor.rest;

import io.bootify.libreri.autor.model.AutorDTO;
import io.bootify.libreri.autor.service.AutorService;
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
@RequestMapping(value = "/api/autors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorResource {

    private final AutorService autorService;

    public AutorResource(final AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> getAllAutors() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping("/{idAutor}")
    public ResponseEntity<AutorDTO> getAutor(
            @PathVariable(name = "idAutor") final Integer idAutor) {
        return ResponseEntity.ok(autorService.get(idAutor));
    }

    @PostMapping
    public ResponseEntity<Integer> createAutor(@RequestBody @Valid final AutorDTO autorDTO) {
        final Integer createdIdAutor = autorService.create(autorDTO);
        return new ResponseEntity<>(createdIdAutor, HttpStatus.CREATED);
    }

    @PutMapping("/{idAutor}")
    public ResponseEntity<Integer> updateAutor(
            @PathVariable(name = "idAutor") final Integer idAutor,
            @RequestBody @Valid final AutorDTO autorDTO) {
        autorService.update(idAutor, autorDTO);
        return ResponseEntity.ok(idAutor);
    }

    @DeleteMapping("/{idAutor}")
    public ResponseEntity<Void> deleteAutor(@PathVariable(name = "idAutor") final Integer idAutor) {
        autorService.delete(idAutor);
        return ResponseEntity.noContent().build();
    }

}
