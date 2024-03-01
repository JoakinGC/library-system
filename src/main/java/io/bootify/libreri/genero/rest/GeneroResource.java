package io.bootify.libreri.genero.rest;

import io.bootify.libreri.genero.model.GeneroDTO;
import io.bootify.libreri.genero.service.GeneroService;
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
@RequestMapping(value = "/api/generos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneroResource {

    private final GeneroService generoService;

    public GeneroResource(final GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAllGeneros() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping("/{idGenero}")
    public ResponseEntity<GeneroDTO> getGenero(
            @PathVariable(name = "idGenero") final Integer idGenero) {
        return ResponseEntity.ok(generoService.get(idGenero));
    }

    @PostMapping
    public ResponseEntity<Integer> createGenero(@RequestBody @Valid final GeneroDTO generoDTO) {
        final Integer createdIdGenero = generoService.create(generoDTO);
        return new ResponseEntity<>(createdIdGenero, HttpStatus.CREATED);
    }

    @PutMapping("/{idGenero}")
    public ResponseEntity<Integer> updateGenero(
            @PathVariable(name = "idGenero") final Integer idGenero,
            @RequestBody @Valid final GeneroDTO generoDTO) {
        generoService.update(idGenero, generoDTO);
        return ResponseEntity.ok(idGenero);
    }

    @DeleteMapping("/{idGenero}")
    public ResponseEntity<Void> deleteGenero(
            @PathVariable(name = "idGenero") final Integer idGenero) {
        generoService.delete(idGenero);
        return ResponseEntity.noContent().build();
    }

}
