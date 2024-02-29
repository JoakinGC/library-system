package io.bootify.libreri.roles.model;

import jakarta.validation.constraints.Size;


public class RolesDTO {

    private Integer idRol;

    @Size(max = 40)
    private String rol;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(final Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(final String rol) {
        this.rol = rol;
    }

}
