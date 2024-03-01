package io.bootify.libreri.prestamo.rest;

import io.bootify.libreri.errors.ExceptionNoFoundPrestamo;
import io.bootify.libreri.prestamo.model.PrestamoDTO;
import io.bootify.libreri.prestamo.service.PrestamoService;
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
@RequestMapping(value = "/api/prestamos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoResource {

    private final PrestamoService prestamoService;

    public PrestamoResource(final PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> getAllPrestamos() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @GetMapping("/{idPrestamo}")
    public ResponseEntity<PrestamoDTO> getPrestamo(
            @PathVariable(name = "idPrestamo") final Integer idPrestamo) throws ExceptionNoFoundPrestamo {
        return ResponseEntity.ok(prestamoService.get(idPrestamo));
    }

    @PostMapping
    public ResponseEntity<Integer> createPrestamo(
            @RequestBody @Valid final PrestamoDTO prestamoDTO) {
        final Integer createdIdPrestamo = prestamoService.create(prestamoDTO);
        return new ResponseEntity<>(createdIdPrestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{idPrestamo}")
    public ResponseEntity<Integer> updatePrestamo(
            @PathVariable(name = "idPrestamo") final Integer idPrestamo,
            @RequestBody @Valid final PrestamoDTO prestamoDTO) {
        prestamoService.update(idPrestamo, prestamoDTO);
        return ResponseEntity.ok(idPrestamo);
    }

    @DeleteMapping("/{idPrestamo}")
    public ResponseEntity<Void> deletePrestamo(
            @PathVariable(name = "idPrestamo") final Integer idPrestamo) {
        prestamoService.delete(idPrestamo);
        return ResponseEntity.noContent().build();
    }

}
