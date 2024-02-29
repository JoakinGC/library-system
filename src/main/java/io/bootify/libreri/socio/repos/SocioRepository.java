package io.bootify.libreri.socio.repos;

import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.socio.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SocioRepository extends JpaRepository<Socio, Integer> {

    Socio findFirstByPrestamo(Prestamo prestamo);

}
