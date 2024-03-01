package io.bootify.libreri.usuario.repos;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.roles.domain.Roles;
import io.bootify.libreri.usuario.domain.Usuario;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {



    Usuario findFirstByFichadoUserFichadoes(Fichado fichado);

    Usuario findFirstByRol(Roles roles);

    List<Usuario> findAllByFichadoUserFichadoes(Fichado fichado);

    Usuario findByNombreAndContresena(String nombre, String contresena);


    @Query("select u from Usuario as u where u.nombre  = ?1")
    Optional<Usuario> getName(String name);
}
