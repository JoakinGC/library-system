package io.bootify.libreri.editorial.model;

import jakarta.validation.constraints.Size;
import java.util.List;


public class EditorialDTO {

    private Integer idEditorial;

    @Size(max = 40)
    private String nombre;

    private List<Integer> libroEditorialLibroses;

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(final Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getLibroEditorialLibroses() {
        return libroEditorialLibroses;
    }

    public void setLibroEditorialLibroses(final List<Integer> libroEditorialLibroses) {
        this.libroEditorialLibroses = libroEditorialLibroses;
    }

}
