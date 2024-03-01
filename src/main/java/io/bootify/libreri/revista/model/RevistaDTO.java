package io.bootify.libreri.revista.model;

import jakarta.validation.constraints.Size;


public class RevistaDTO {

    private Integer idRevista;

    @Size(max = 40)
    private String titulo;


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


    @Override
    public String toString() {
        return "RevistaDTO{" +
                "idRevista=" + idRevista +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
