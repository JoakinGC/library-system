package io.bootify.libreri.revista.rest;

import io.bootify.libreri.revista.model.RevistaDTO;
import io.bootify.libreri.revista.service.RevistaService;
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
@RequestMapping(value = "/api/revistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class RevistaResource {

    private final RevistaService revistaService;

    public RevistaResource(final RevistaService revistaService) {
        this.revistaService = revistaService;
    }

    @GetMapping
    public ResponseEntity<List<RevistaDTO>> getAllRevistas() {
        return ResponseEntity.ok(revistaService.findAll());
    }

    @GetMapping("/{idRevista}")
    public ResponseEntity<RevistaDTO> getRevista(
            @PathVariable(name = "idRevista") final Integer idRevista) {
        return ResponseEntity.ok(revistaService.get(idRevista));
    }

    @PostMapping
    public ResponseEntity<Integer> createRevista(@RequestBody @Valid final RevistaDTO revistaDTO) {
        final Integer createdIdRevista = revistaService.create(revistaDTO);
        return new ResponseEntity<>(createdIdRevista, HttpStatus.CREATED);
    }

    @PutMapping("/{idRevista}")
    public ResponseEntity<Integer> updateRevista(
            @PathVariable(name = "idRevista") final Integer idRevista,
            @RequestBody @Valid final RevistaDTO revistaDTO) {
        revistaService.update(idRevista, revistaDTO);
        return ResponseEntity.ok(idRevista);
    }

    @DeleteMapping("/{idRevista}")
    public ResponseEntity<Void> deleteRevista(
            @PathVariable(name = "idRevista") final Integer idRevista) {
        revistaService.delete(idRevista);
        return ResponseEntity.noContent().build();
    }

}
