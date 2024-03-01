package io.bootify.libreri.roles.domain;

import io.bootify.libreri.usuario.domain.Usuario;
<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
=======
import jakarta.persistence.*;

>>>>>>> Joaquin-System
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Roles {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

<<<<<<< HEAD
    @Column(length = 40)
    private String rol;
=======
    @Column
    @Enumerated(EnumType.STRING)
    private ERoles rol;

>>>>>>> Joaquin-System

    @OneToMany(mappedBy = "rol")
    private Set<Usuario> rolUsuarios;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(final Integer idRol) {
        this.idRol = idRol;
    }

<<<<<<< HEAD
    public String getRol() {
        return rol;
    }

    public void setRol(final String rol) {
=======
    public ERoles getRol() {
        return rol;
    }

    public void setRol(ERoles rol) {
>>>>>>> Joaquin-System
        this.rol = rol;
    }

    public Set<Usuario> getRolUsuarios() {
        return rolUsuarios;
    }

    public void setRolUsuarios(final Set<Usuario> rolUsuarios) {
        this.rolUsuarios = rolUsuarios;
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
