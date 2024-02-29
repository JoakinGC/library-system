package io.bootify.libreri.libros.repos;

import io.bootify.libreri.autor.domain.Autor;
import io.bootify.libreri.libros.domain.Libros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibrosRepository extends JpaRepository<Libros, Integer> {

    Libros findFirstByLibroAutorAutors(Autor autor);

    List<Libros> findAllByLibroAutorAutors(Autor autor);

}
