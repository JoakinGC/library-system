package io.bootify.libreri.fichado.model;

import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class FichadoDTO {

    private Integer idFichado;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaFichaje;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime horaEntrada;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime horaSalida;

    private Double tiempoTotalDia;

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

}
