package io.bootify.libreri.genero.repos;

import io.bootify.libreri.genero.domain.Genero;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.revista.domain.Revista;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    Genero findFirstByLibroGeneroLibroses(Libros libros);

    Genero findFirstByGeneroRevistaRevistas(Revista revista);

    List<Genero> findAllByLibroGeneroLibroses(Libros libros);

    List<Genero> findAllByGeneroRevistaRevistas(Revista revista);

}
