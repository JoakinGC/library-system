package io.bootify.libreri.prestamo.repos;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    Prestamo findFirstByEjemplar(Ejemplar ejemplar);

    Prestamo findFirstByEmple(Usuario usuario);

}
