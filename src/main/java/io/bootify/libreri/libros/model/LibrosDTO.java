package io.bootify.libreri.libros.model;

import jakarta.validation.constraints.Size;
import java.util.List;


public class LibrosDTO {

    private Integer isbn;

    @Size(max = 40)
    private String titulo;

    private List<Integer> libroAutorAutors;

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(final Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public List<Integer> getLibroAutorAutors() {
        return libroAutorAutors;
    }

    public void setLibroAutorAutors(final List<Integer> libroAutorAutors) {
        this.libroAutorAutors = libroAutorAutors;
    }

}
