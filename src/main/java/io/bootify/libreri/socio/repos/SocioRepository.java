package io.bootify.libreri.socio.repos;

import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.socio.domain.Socio;
<<<<<<< HEAD
=======
import io.bootify.libreri.usuario.domain.Usuario;
>>>>>>> Joaquin-System
import org.springframework.data.jpa.repository.JpaRepository;


public interface SocioRepository extends JpaRepository<Socio, Integer> {

<<<<<<< HEAD
    Socio findFirstByPrestamo(Prestamo prestamo);

=======
>>>>>>> Joaquin-System
}
