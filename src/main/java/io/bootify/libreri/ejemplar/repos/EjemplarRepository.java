package io.bootify.libreri.ejemplar.repos;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.revista.domain.Revista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {

    Ejemplar findFirstByLibro(Libros libros);

    Ejemplar findFirstByRevista(Revista revista);

    @Query("SELECT DISTINCT e.libro FROM Ejemplar e WHERE e.libro IS NOT NULL")
    List<Libros> findAllLibrosWithEjemplar();

    @Query("SELECT DISTINCT e.revista FROM Ejemplar e WHERE e.revista IS NOT NULL")
    List<Revista> findAllRevistasWithEjemplar();

}
