package io.bootify.libreri.prestamo.repos;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.socio.domain.Socio;
import io.bootify.libreri.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    Prestamo findFirstByEjemplar(Ejemplar ejemplar);

    List<Prestamo> findByEjemplar(Ejemplar ejemplar);

    List<Prestamo> findBySocio(Socio socio);

    Prestamo findFirstBySocio(Socio socio);

    Prestamo findFirstByEmple(Usuario usuario);


    @Query("SELECT p FROM Prestamo p WHERE p.idPrestamo = :idPrestamo")
    Prestamo findByIdPrestamo(@Param("idPrestamo") Integer idPrestamo);

    @Query("SELECT p FROM Prestamo p WHERE p.ejemplar.libro.titulo LIKE %:titulo%")
    List<Prestamo> findByNombreLibro(@Param("titulo") String titulo);

    @Query("SELECT p FROM Prestamo p WHERE p.ejemplar.libro.isbn = :isbn")
    Prestamo findByIsbnLibro(@Param("isbn") Integer isbn);
}
