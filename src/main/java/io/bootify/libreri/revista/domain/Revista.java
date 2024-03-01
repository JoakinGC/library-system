package io.bootify.libreri.revista.domain;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.genero.domain.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Revista {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRevista;

    @Column(length = 40)
    private String titulo;

    @ManyToMany(mappedBy = "generoRevistaRevistas")
    private Set<Genero> generoRevistaGeneroes;

<<<<<<< HEAD
    @OneToMany(mappedBy = "revista")
    private Set<Ejemplar> revistaEjemplars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ejemplar_id")
    private Ejemplar ejemplar;

=======
>>>>>>> Joaquin-System
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Set<Genero> getGeneroRevistaGeneroes() {
        return generoRevistaGeneroes;
    }

    public void setGeneroRevistaGeneroes(final Set<Genero> generoRevistaGeneroes) {
        this.generoRevistaGeneroes = generoRevistaGeneroes;
    }

<<<<<<< HEAD
    public Set<Ejemplar> getRevistaEjemplars() {
        return revistaEjemplars;
    }

    public void setRevistaEjemplars(final Set<Ejemplar> revistaEjemplars) {
        this.revistaEjemplars = revistaEjemplars;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(final Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
=======
>>>>>>> Joaquin-System

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
        if (!(o instanceof Revista revista)) return false;
        return Objects.equals(idRevista, revista.idRevista) && Objects.equals(titulo, revista.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRevista, titulo);
    }

    @Override
    public String toString() {
        return "Revista{" +
                "idRevista=" + idRevista +
                ", titulo='" + titulo + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
>>>>>>> Joaquin-System
}
