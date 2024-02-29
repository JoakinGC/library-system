package io.bootify.libreri.ejemplar.repos;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.revista.domain.Revista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {

    Ejemplar findFirstByLibro(Libros libros);

    Ejemplar findFirstByRevista(Revista revista);

}
