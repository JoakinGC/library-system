package io.bootify.libreri.genero.model;

import jakarta.validation.constraints.Size;
import java.util.List;


public class GeneroDTO {

    private Integer idGenero;

    @Size(max = 40)
    private String nombre;

    private List<Integer> libroGeneroLibroses;

    private List<Integer> generoRevistaRevistas;

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(final Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getLibroGeneroLibroses() {
        return libroGeneroLibroses;
    }

    public void setLibroGeneroLibroses(final List<Integer> libroGeneroLibroses) {
        this.libroGeneroLibroses = libroGeneroLibroses;
    }

    public List<Integer> getGeneroRevistaRevistas() {
        return generoRevistaRevistas;
    }

    public void setGeneroRevistaRevistas(final List<Integer> generoRevistaRevistas) {
        this.generoRevistaRevistas = generoRevistaRevistas;
    }

}
