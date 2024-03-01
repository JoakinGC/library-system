package io.bootify.libreri.socio.model;

import jakarta.validation.constraints.Size;


public class SocioDTO {

    private Integer idSocio;

    @Size(max = 40)
    private String nombre;

    @Size(max = 40)
    private String apellido;

    private Integer telefono;

    @Size(max = 9)
    private String dni;

    @Size(max = 50)
    private String direccion;

    private Integer multa;

    private Boolean activo;

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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
