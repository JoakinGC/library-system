package io.bootify.libreri.usuario.domain;

import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.roles.domain.Roles;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario  {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(length = 40)
    private String nombre;

    @Column(length = 40)
    private String apellido;

    @Column
    private OffsetDateTime diaAlta;

    @Column
    private OffsetDateTime diaBaja;

    @Column(length = 20)
    private String contresena;

    @Column(length = 9)
    private String dni;

    @Column
    private Integer edad;

    @Column
    private Integer idSuper;

    @OneToMany(mappedBy = "emple")
    private Set<Prestamo> emplePrestamoes;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "FichadoUser",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idFichado")
    )
    private Set<Fichado> fichadoUserFichadoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Roles rol;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Set<Prestamo> getEmplePrestamoes() {
        return emplePrestamoes;
    }

    public void setEmplePrestamoes(final Set<Prestamo> emplePrestamoes) {
        this.emplePrestamoes = emplePrestamoes;
    }

    public Set<Fichado> getFichadoUserFichadoes() {
        return fichadoUserFichadoes;
    }

    public void setFichadoUserFichadoes(final Set<Fichado> fichadoUserFichadoes) {
        this.fichadoUserFichadoes = fichadoUserFichadoes;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(final Roles rol) {
        this.rol = rol;
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


    @Override
    public String toString() {
        return "Usuario{" +
                "idUser=" + idUser +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", diaAlta=" + diaAlta +
                ", contresena='" + contresena + '\'' +
                ", rol=" + rol.toString() +
                '}';
    }
}
