package io.bootify.libreri.socio.rest;

import io.bootify.libreri.socio.model.SocioDTO;
import io.bootify.libreri.socio.service.SocioService;
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
@RequestMapping(value = "/api/socios", produces = MediaType.APPLICATION_JSON_VALUE)
public class SocioResource {

    private final SocioService socioService;

    public SocioResource(final SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    public ResponseEntity<List<SocioDTO>> getAllSocios() {
        return ResponseEntity.ok(socioService.findAll());
    }

    @GetMapping("/{idSocio}")
    public ResponseEntity<SocioDTO> getSocio(
            @PathVariable(name = "idSocio") final Integer idSocio) {
        return ResponseEntity.ok(socioService.get(idSocio));
    }

    @PostMapping
    public ResponseEntity<Integer> createSocio(@RequestBody @Valid final SocioDTO socioDTO) {
        final Integer createdIdSocio = socioService.create(socioDTO);
        return new ResponseEntity<>(createdIdSocio, HttpStatus.CREATED);
    }

    @PutMapping("/{idSocio}")
    public ResponseEntity<Integer> updateSocio(
            @PathVariable(name = "idSocio") final Integer idSocio,
            @RequestBody @Valid final SocioDTO socioDTO) {
        socioService.update(idSocio, socioDTO);
        return ResponseEntity.ok(idSocio);
    }

    @DeleteMapping("/{idSocio}")
    public ResponseEntity<Void> deleteSocio(@PathVariable(name = "idSocio") final Integer idSocio) {
        socioService.delete(idSocio);
        return ResponseEntity.noContent().build();
    }

}
