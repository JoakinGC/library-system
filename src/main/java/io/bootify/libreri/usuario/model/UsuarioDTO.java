package io.bootify.libreri.usuario.model;

import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


public class UsuarioDTO {

    private Integer idUser;

    @Size(max = 40)
    private String nombre;

    @Size(max = 40)
    private String apellido;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime diaAlta;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime diaBaja;

    @Size(max = 20)
    private String contresena;

    @Size(max = 9)
    private String dni;

    private Integer edad;

    private Integer idSuper;

    private List<Integer> fichadoUserFichadoes;

    private Integer rol;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
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

    public OffsetDateTime getDiaAlta() {
        return diaAlta;
    }

    public void setDiaAlta(final OffsetDateTime diaAlta) {
        this.diaAlta = diaAlta;
    }

    public OffsetDateTime getDiaBaja() {
        return diaBaja;
    }

    public void setDiaBaja(final OffsetDateTime diaBaja) {
        this.diaBaja = diaBaja;
    }

    public String getContresena() {
        return contresena;
    }

    public void setContresena(final String contresena) {
        this.contresena = contresena;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(final String dni) {
        this.dni = dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(final Integer edad) {
        this.edad = edad;
    }

    public Integer getIdSuper() {
        return idSuper;
    }

    public void setIdSuper(final Integer idSuper) {
        this.idSuper = idSuper;
    }

    public List<Integer> getFichadoUserFichadoes() {
        return fichadoUserFichadoes;
    }

    public void setFichadoUserFichadoes(final List<Integer> fichadoUserFichadoes) {
        this.fichadoUserFichadoes = fichadoUserFichadoes;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(final Integer rol) {
        this.rol = rol;
    }

}
