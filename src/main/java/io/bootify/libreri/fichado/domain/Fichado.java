package io.bootify.libreri.fichado.domain;

import io.bootify.libreri.usuario.domain.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Fichado {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFichado;

    @Column
    private OffsetDateTime fechaFichaje;

    @Column
    private OffsetDateTime horaEntrada;

    @Column
    private OffsetDateTime horaSalida;

    @Column
    private Double tiempoTotalDia;

    @ManyToMany(mappedBy = "fichadoUserFichadoes")
    private Set<Usuario> fichadoUserUsuarios;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdFichado() {
        return idFichado;
    }

    public void setIdFichado(final Integer idFichado) {
        this.idFichado = idFichado;
    }

    public OffsetDateTime getFechaFichaje() {
        return fechaFichaje;
    }

    public void setFechaFichaje(final OffsetDateTime fechaFichaje) {
        this.fechaFichaje = fechaFichaje;
    }

    public OffsetDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(final OffsetDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public OffsetDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(final OffsetDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Double getTiempoTotalDia() {
        return tiempoTotalDia;
    }

    public void setTiempoTotalDia(final Double tiempoTotalDia) {
        this.tiempoTotalDia = tiempoTotalDia;
    }

    public Set<Usuario> getFichadoUserUsuarios() {
        return fichadoUserUsuarios;
    }

    public void setFichadoUserUsuarios(final Set<Usuario> fichadoUserUsuarios) {
        this.fichadoUserUsuarios = fichadoUserUsuarios;
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
