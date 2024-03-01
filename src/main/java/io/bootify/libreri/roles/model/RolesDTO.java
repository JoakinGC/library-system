package io.bootify.libreri.roles.model;

<<<<<<< HEAD
=======
import io.bootify.libreri.roles.domain.ERoles;
>>>>>>> Joaquin-System
import jakarta.validation.constraints.Size;


public class RolesDTO {

    private Integer idRol;

    @Size(max = 40)
<<<<<<< HEAD
    private String rol;
=======
    private ERoles rol;
>>>>>>> Joaquin-System

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(final Integer idRol) {
        this.idRol = idRol;
    }

<<<<<<< HEAD
    public String getRol() {
        return rol;
    }

    public void setRol(final String rol) {
        this.rol = rol;
    }

=======
    public ERoles getRol() {
        return rol;
    }

    public void setRol(ERoles rol) {
        this.rol = rol;
    }
>>>>>>> Joaquin-System
}
