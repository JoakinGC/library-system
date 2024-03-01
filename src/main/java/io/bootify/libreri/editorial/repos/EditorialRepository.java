package io.bootify.libreri.editorial.repos;

import io.bootify.libreri.editorial.domain.Editorial;
import io.bootify.libreri.libros.domain.Libros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EditorialRepository extends JpaRepository<Editorial, Integer> {

    Editorial findFirstByLibroEditorialLibroses(Libros libros);

    List<Editorial> findAllByLibroEditorialLibroses(Libros libros);

}
