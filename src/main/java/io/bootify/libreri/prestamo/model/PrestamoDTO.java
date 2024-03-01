package io.bootify.libreri.prestamo.model;

<<<<<<< HEAD
=======
import io.bootify.libreri.prestamo.domain.ETipos;
>>>>>>> Joaquin-System
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class PrestamoDTO {

    private Integer idPrestamo;

<<<<<<< HEAD
    @Size(max = 8)
    private String tipo;
=======
    private ETipos tipo;
>>>>>>> Joaquin-System

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaPrestamo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaEntrega;

    private Integer ejemplar;

<<<<<<< HEAD
    private Integer emple;

=======
    private Integer socio;

    private Integer emple;

    private  Boolean entregado;

>>>>>>> Joaquin-System
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(final Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

<<<<<<< HEAD
    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
=======
    public ETipos getTipo() {
        return tipo;
    }

    public void setTipo(ETipos tipo) {
>>>>>>> Joaquin-System
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

<<<<<<< HEAD
=======
    public Integer getSocio() {
        return socio;
    }

    public void setSocio(final Integer socio) {
        this.socio = socio;
    }

>>>>>>> Joaquin-System
    public Integer getEmple() {
        return emple;
    }

    public void setEmple(final Integer emple) {
        this.emple = emple;
    }

<<<<<<< HEAD
=======
    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }
>>>>>>> Joaquin-System
}
