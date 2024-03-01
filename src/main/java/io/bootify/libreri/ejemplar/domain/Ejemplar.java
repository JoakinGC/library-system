package io.bootify.libreri.ejemplar.domain;

import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.revista.domain.Revista;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Ejemplar {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEjemplar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libros libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revista_id")
    private Revista revista;

<<<<<<< HEAD
    @OneToMany(mappedBy = "ejemplar")
    private Set<Revista> ejemplarRevistas;
=======
>>>>>>> Joaquin-System

    @OneToMany(mappedBy = "ejemplar")
    private Set<Prestamo> ejemplarPrestamoes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(final Integer idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(final Libros libro) {
        this.libro = libro;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(final Revista revista) {
        this.revista = revista;
    }

<<<<<<< HEAD
    public Set<Revista> getEjemplarRevistas() {
        return ejemplarRevistas;
    }

    public void setEjemplarRevistas(final Set<Revista> ejemplarRevistas) {
        this.ejemplarRevistas = ejemplarRevistas;
    }

=======
>>>>>>> Joaquin-System
    public Set<Prestamo> getEjemplarPrestamoes() {
        return ejemplarPrestamoes;
    }

    public void setEjemplarPrestamoes(final Set<Prestamo> ejemplarPrestamoes) {
        this.ejemplarPrestamoes = ejemplarPrestamoes;
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
