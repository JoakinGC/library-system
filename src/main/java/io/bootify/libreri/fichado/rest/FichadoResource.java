package io.bootify.libreri.fichado.rest;

import io.bootify.libreri.fichado.model.FichadoDTO;
import io.bootify.libreri.fichado.service.FichadoService;
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
@RequestMapping(value = "/api/fichados", produces = MediaType.APPLICATION_JSON_VALUE)
public class FichadoResource {

    private final FichadoService fichadoService;

    public FichadoResource(final FichadoService fichadoService) {
        this.fichadoService = fichadoService;
    }

    @GetMapping
    public ResponseEntity<List<FichadoDTO>> getAllFichados() {
        return ResponseEntity.ok(fichadoService.findAll());
    }

    @GetMapping("/{idFichado}")
    public ResponseEntity<FichadoDTO> getFichado(
            @PathVariable(name = "idFichado") final Integer idFichado) {
        return ResponseEntity.ok(fichadoService.get(idFichado));
    }

    @PostMapping
    public ResponseEntity<Integer> createFichado(@RequestBody @Valid final FichadoDTO fichadoDTO) {
        final Integer createdIdFichado = fichadoService.create(fichadoDTO);
        return new ResponseEntity<>(createdIdFichado, HttpStatus.CREATED);
    }

    @PutMapping("/{idFichado}")
    public ResponseEntity<Integer> updateFichado(
            @PathVariable(name = "idFichado") final Integer idFichado,
            @RequestBody @Valid final FichadoDTO fichadoDTO) {
        fichadoService.update(idFichado, fichadoDTO);
        return ResponseEntity.ok(idFichado);
    }

    @DeleteMapping("/{idFichado}")
    public ResponseEntity<Void> deleteFichado(
            @PathVariable(name = "idFichado") final Integer idFichado) {
        fichadoService.delete(idFichado);
        return ResponseEntity.noContent().build();
    }

}
