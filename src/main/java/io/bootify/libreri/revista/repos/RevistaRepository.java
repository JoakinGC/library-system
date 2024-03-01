package io.bootify.libreri.revista.repos;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.revista.domain.Revista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RevistaRepository extends JpaRepository<Revista, Integer> {

<<<<<<< HEAD
    Revista findFirstByEjemplar(Ejemplar ejemplar);

=======
>>>>>>> Joaquin-System
}
