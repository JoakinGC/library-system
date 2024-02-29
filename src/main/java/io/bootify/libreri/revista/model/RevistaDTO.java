package io.bootify.libreri.revista.model;

import jakarta.validation.constraints.Size;


public class RevistaDTO {

    private Integer idRevista;

    @Size(max = 40)
    private String titulo;

    private Integer ejemplar;

    public Integer getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(final Integer idRevista) {
        this.idRevista = idRevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(final Integer ejemplar) {
        this.ejemplar = ejemplar;
    }

}
