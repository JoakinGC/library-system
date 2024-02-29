package io.bootify.libreri.prestamo.model;

import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class PrestamoDTO {

    private Integer idPrestamo;

    @Size(max = 8)
    private String tipo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaPrestamo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaEntrega;

    private Integer ejemplar;

    private Integer emple;

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(final Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
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

    public Integer getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(final Integer ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Integer getEmple() {
        return emple;
    }

    public void setEmple(final Integer emple) {
        this.emple = emple;
    }

}
