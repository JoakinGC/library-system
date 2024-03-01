package io.bootify.libreri.socio.domain;

import io.bootify.libreri.prestamo.domain.Prestamo;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Socio {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSocio;

    @Column(length = 40)
    private String nombre;

    @Column(length = 40)
    private String apellido;

    @Column
    private Integer telefono;

    @Column(length = 9)
    private String dni;

    @Column(length = 50)
    private String direccion;

    @Column
    private Integer multa;

    @Column
    private  Boolean activo;

    @OneToMany(mappedBy = "socio")
    private Set<Prestamo> socioPrestamoes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(final Integer idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(final Integer telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(final String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(final String direccion) {
        this.direccion = direccion;
    }

    public Integer getMulta() {
        return multa;
    }

    public void setMulta(final Integer multa) {
        this.multa = multa;
    }


    public Set<Prestamo> getSocioPrestamoes() {
        return socioPrestamoes;
    }

    public void setSocioPrestamoes(final Set<Prestamo> socioPrestamoes) {
        this.socioPrestamoes = socioPrestamoes;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
