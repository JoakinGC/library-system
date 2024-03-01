package io.bootify.libreri.ejemplar.rest;

import io.bootify.libreri.ejemplar.model.EjemplarDTO;
import io.bootify.libreri.ejemplar.service.EjemplarService;
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
@RequestMapping(value = "/api/ejemplars", produces = MediaType.APPLICATION_JSON_VALUE)
public class EjemplarResource {

    private final EjemplarService ejemplarService;

    public EjemplarResource(final EjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
    }

    @GetMapping
    public ResponseEntity<List<EjemplarDTO>> getAllEjemplars() {
        return ResponseEntity.ok(ejemplarService.findAll());
    }

    @GetMapping("/{idEjemplar}")
    public ResponseEntity<EjemplarDTO> getEjemplar(
            @PathVariable(name = "idEjemplar") final Integer idEjemplar) {
        return ResponseEntity.ok(ejemplarService.get(idEjemplar));
    }

    @PostMapping
    public ResponseEntity<Integer> createEjemplar(
            @RequestBody @Valid final EjemplarDTO ejemplarDTO) {
        final Integer createdIdEjemplar = ejemplarService.create(ejemplarDTO);
        return new ResponseEntity<>(createdIdEjemplar, HttpStatus.CREATED);
    }

    @PutMapping("/{idEjemplar}")
    public ResponseEntity<Integer> updateEjemplar(
            @PathVariable(name = "idEjemplar") final Integer idEjemplar,
            @RequestBody @Valid final EjemplarDTO ejemplarDTO) {
        ejemplarService.update(idEjemplar, ejemplarDTO);
        return ResponseEntity.ok(idEjemplar);
    }

    @DeleteMapping("/{idEjemplar}")
    public ResponseEntity<Void> deleteEjemplar(
            @PathVariable(name = "idEjemplar") final Integer idEjemplar) {
        ejemplarService.delete(idEjemplar);
        return ResponseEntity.noContent().build();
    }

}
