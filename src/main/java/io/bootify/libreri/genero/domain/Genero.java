package io.bootify.libreri.genero.domain;

import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.revista.domain.Revista;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Genero {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenero;

    @Column(length = 40)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "LibroGenero",
            joinColumns = @JoinColumn(name = "idGenero"),
            inverseJoinColumns = @JoinColumn(name = "isbn")
    )
    private Set<Libros> libroGeneroLibroses;

    @ManyToMany
    @JoinTable(
            name = "GeneroRevista",
            joinColumns = @JoinColumn(name = "idGenero"),
            inverseJoinColumns = @JoinColumn(name = "idRevista")
    )
    private Set<Revista> generoRevistaRevistas;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Set<Libros> getLibroGeneroLibroses() {
        return libroGeneroLibroses;
    }

    public void setLibroGeneroLibroses(final Set<Libros> libroGeneroLibroses) {
        this.libroGeneroLibroses = libroGeneroLibroses;
    }

    public Set<Revista> getGeneroRevistaRevistas() {
        return generoRevistaRevistas;
    }

    public void setGeneroRevistaRevistas(final Set<Revista> generoRevistaRevistas) {
        this.generoRevistaRevistas = generoRevistaRevistas;
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

}
