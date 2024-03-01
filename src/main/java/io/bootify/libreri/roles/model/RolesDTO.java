package io.bootify.libreri.roles.model;

import io.bootify.libreri.roles.domain.ERoles;
import jakarta.validation.constraints.Size;


public class RolesDTO {

    private Integer idRol;

    @Size(max = 40)
    private ERoles rol;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(final Integer idRol) {
        this.idRol = idRol;
    }

    public ERoles getRol() {
        return rol;
    }

    public void setRol(ERoles rol) {
        this.rol = rol;
    }
}
