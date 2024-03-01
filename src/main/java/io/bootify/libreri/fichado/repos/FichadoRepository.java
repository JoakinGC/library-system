package io.bootify.libreri.fichado.repos;

import io.bootify.libreri.fichado.domain.Fichado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FichadoRepository extends JpaRepository<Fichado, Integer> {
}
