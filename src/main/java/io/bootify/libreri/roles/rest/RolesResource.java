package io.bootify.libreri.roles.rest;

import io.bootify.libreri.roles.model.RolesDTO;
import io.bootify.libreri.roles.service.RolesService;
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
@RequestMapping(value = "/api/roless", produces = MediaType.APPLICATION_JSON_VALUE)
public class RolesResource {

    private final RolesService rolesService;

    public RolesResource(final RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<RolesDTO>> getAllRoless() {
        return ResponseEntity.ok(rolesService.findAll());
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<RolesDTO> getRoles(@PathVariable(name = "idRol") final Integer idRol) {
        return ResponseEntity.ok(rolesService.get(idRol));
    }

    @PostMapping
    public ResponseEntity<Integer> createRoles(@RequestBody @Valid final RolesDTO rolesDTO) {
        final Integer createdIdRol = rolesService.create(rolesDTO);
        return new ResponseEntity<>(createdIdRol, HttpStatus.CREATED);
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<Integer> updateRoles(@PathVariable(name = "idRol") final Integer idRol,
            @RequestBody @Valid final RolesDTO rolesDTO) {
        rolesService.update(idRol, rolesDTO);
        return ResponseEntity.ok(idRol);
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Void> deleteRoles(@PathVariable(name = "idRol") final Integer idRol) {
        rolesService.delete(idRol);
        return ResponseEntity.noContent().build();
    }

}
