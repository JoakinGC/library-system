package io.bootify.libreri.libros.rest;

import io.bootify.libreri.libros.model.LibrosDTO;
import io.bootify.libreri.libros.service.LibrosService;
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
@RequestMapping(value = "/api/libross", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibrosResource {

    private final LibrosService librosService;

    public LibrosResource(final LibrosService librosService) {
        this.librosService = librosService;
    }

    @GetMapping
    public ResponseEntity<List<LibrosDTO>> getAllLibross() {
        return ResponseEntity.ok(librosService.findAll());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LibrosDTO> getLibros(@PathVariable(name = "isbn") final Integer isbn) {
        return ResponseEntity.ok(librosService.get(isbn));
    }

    @PostMapping
    public ResponseEntity<Integer> createLibros(@RequestBody @Valid final LibrosDTO librosDTO) {
        final Integer createdIsbn = librosService.create(librosDTO);
        return new ResponseEntity<>(createdIsbn, HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Integer> updateLibros(@PathVariable(name = "isbn") final Integer isbn,
            @RequestBody @Valid final LibrosDTO librosDTO) {
        librosService.update(isbn, librosDTO);
        return ResponseEntity.ok(isbn);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteLibros(@PathVariable(name = "isbn") final Integer isbn) {
        librosService.delete(isbn);
        return ResponseEntity.noContent().build();
    }

}
