package io.bootify.libreri.autor.repos;

import io.bootify.libreri.autor.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
