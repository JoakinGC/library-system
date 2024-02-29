package io.bootify.libreri.usuario.repos;

import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.roles.domain.Roles;
import io.bootify.libreri.usuario.domain.Usuario;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> { //JpaRepository hace que todas las relaciones de JPA se hagan automáticamente

    Usuario findFirstByFichadoUserFichadoes(Fichado fichado);

    Usuario findFirstByRol(Roles roles);

    List<Usuario> findAllByFichadoUserFichadoes(Fichado fichado);

    Usuario findByNombreAndContresena(String nombre, String contresena);

    @Query(value = "SELECT \n" +
            "    u.id_user AS id, \n" +
            "    u.nombre, \n" +
            "    u.apellido, \n" +
            "    COUNT(p.id_prestamo),\n" +
            "    COUNT(p.fecha_entrega)" +
            "FROM \n" +
            "    usuario u\n" +
            "INNER JOIN \n" +
            "    prestamo p ON p.emple_id = u.id_user\n" +
            "WHERE \n" +
            "    rol_id = (SELECT id_rol FROM roles WHERE rol = 'empleado')\n" +
            "GROUP BY \n" +
                "    u.id_user;", nativeQuery = true)
    List<Map<String, Object>> productividad();

}
