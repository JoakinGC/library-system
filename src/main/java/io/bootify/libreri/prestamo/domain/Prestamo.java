package io.bootify.libreri.prestamo.domain;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.socio.domain.Socio;
import io.bootify.libreri.usuario.domain.Usuario;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Prestamo {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;

    @Enumerated(EnumType.STRING)
    private ETipos tipo;

    @Column
    private OffsetDateTime fechaPrestamo;

    @Column
    private OffsetDateTime fechaFin;

    @Column
    private OffsetDateTime fechaEntrega;

    @Column
    private Boolean entregado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ejemplar_id")
    private Ejemplar ejemplar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id")
    private Socio socio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emple_id")
    private Usuario emple;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(final Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public ETipos getTipo() {
        return tipo;
    }

    public void setTipo(ETipos tipo) {
        this.tipo = tipo;
    }

    public OffsetDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(final OffsetDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public OffsetDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(final OffsetDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public OffsetDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(final OffsetDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(final Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(final Socio socio) {
        this.socio = socio;
    }

    public Usuario getEmple() {
        return emple;
    }

    public void setEmple(final Usuario emple) {
        this.emple = emple;
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

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }
}
