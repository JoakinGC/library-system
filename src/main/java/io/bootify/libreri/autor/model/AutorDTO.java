package io.bootify.libreri.autor.model;

import jakarta.validation.constraints.Size;


public class AutorDTO {

    private Integer idAutor;

    @Size(max = 40)
    private String nombre;

    @Size(max = 40)
    private String apellido;

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(final Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

}
