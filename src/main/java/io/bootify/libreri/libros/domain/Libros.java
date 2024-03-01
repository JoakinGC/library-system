package io.bootify.libreri.libros.domain;

import io.bootify.libreri.autor.domain.Autor;
import io.bootify.libreri.editorial.domain.Editorial;
import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.genero.domain.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.time.OffsetDateTime;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> Joaquin-System
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Libros {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer isbn;

    @Column(length = 40)
    private String titulo;

    @ManyToMany(mappedBy = "libroGeneroLibroses")
    private Set<Genero> libroGeneroGeneroes;

    @ManyToMany(mappedBy = "libroEditorialLibroses")
    private Set<Editorial> libroEditorialEditorials;

    @ManyToMany
    @JoinTable(
            name = "LibroAutor",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "idAutor")
    )
    private Set<Autor> libroAutorAutors;

    @OneToMany(mappedBy = "libro")
    private Set<Ejemplar> libroEjemplars;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Set<Genero> getLibroGeneroGeneroes() {
        return libroGeneroGeneroes;
    }

    public void setLibroGeneroGeneroes(final Set<Genero> libroGeneroGeneroes) {
        this.libroGeneroGeneroes = libroGeneroGeneroes;
    }

    public Set<Editorial> getLibroEditorialEditorials() {
        return libroEditorialEditorials;
    }

    public void setLibroEditorialEditorials(final Set<Editorial> libroEditorialEditorials) {
        this.libroEditorialEditorials = libroEditorialEditorials;
    }

    public Set<Autor> getLibroAutorAutors() {
        return libroAutorAutors;
    }

    public void setLibroAutorAutors(final Set<Autor> libroAutorAutors) {
        this.libroAutorAutors = libroAutorAutors;
    }

    public Set<Ejemplar> getLibroEjemplars() {
        return libroEjemplars;
    }

    public void setLibroEjemplars(final Set<Ejemplar> libroEjemplars) {
        this.libroEjemplars = libroEjemplars;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

<<<<<<< HEAD
=======

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libros libros)) return false;
        return Objects.equals(isbn, libros.isbn) && Objects.equals(titulo, libros.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, titulo);
    }

    @Override
    public String toString() {
        return "Libros{" +
                "isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
>>>>>>> Joaquin-System
}
