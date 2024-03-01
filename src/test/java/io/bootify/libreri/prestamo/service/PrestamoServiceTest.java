package io.bootify.libreri.prestamo.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import io.bootify.libreri.prestamo.domain.ETipos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.errors.ExceptionNoFoundPrestamo;
import io.bootify.libreri.errors.NotFoundEmpleado;
import io.bootify.libreri.errors.NotFoundEjemplar;
import io.bootify.libreri.errors.NotFoundSocio;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.model.PrestamoDTO;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.socio.domain.Socio;
import io.bootify.libreri.socio.repos.SocioRepository;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrestamoServiceTest {

	private PrestamoRepository prestamoRepository;
	private EjemplarRepository ejemplarRepository;
	private SocioRepository socioRepository;
	private UsuarioRepository usuarioRepository;

	private PrestamoService prestamoService;

	@BeforeEach
	void setUp() {
		prestamoRepository = mock(PrestamoRepository.class);
		ejemplarRepository = mock(EjemplarRepository.class);
		socioRepository = mock(SocioRepository.class);
		usuarioRepository = mock(UsuarioRepository.class);

		prestamoService = new PrestamoService(prestamoRepository, ejemplarRepository, socioRepository, usuarioRepository);
	}

	@Test
	void testFindAll() {
		// Arrange
		Prestamo prestamo = new Prestamo();
		prestamo.setIdPrestamo(1);
		prestamo.setTipo(ETipos.LIBRO);
		prestamo.setFechaPrestamo(OffsetDateTime.now());

		when(prestamoRepository.findAll(Sort.by("idPrestamo"))).thenReturn(Collections.singletonList(prestamo));

		// Act
		List<PrestamoDTO> prestamoDTOs = prestamoService.findAll();

		// Assert
		assertEquals(1, prestamoDTOs.size(), "La cantidad de préstamos no es la esperada");
		assertEquals(1, prestamoDTOs.get(0).getIdPrestamo(), "El ID del préstamo no es el esperado");
		assertEquals(ETipos.LIBRO, prestamoDTOs.get(0).getTipo(), "El tipo de préstamo no es el esperado");
	}

	@Test
	void testGet() throws ExceptionNoFoundPrestamo {
		// Arrange
		Prestamo prestamo = new Prestamo();
		prestamo.setIdPrestamo(1);
		prestamo.setTipo(ETipos.LIBRO);
		prestamo.setFechaPrestamo(OffsetDateTime.now());

		when(prestamoRepository.findById(1)).thenReturn(Optional.of(prestamo));

		// Act
		PrestamoDTO prestamoDTO = prestamoService.get(1);

		// Assert
		assertNotNull(prestamoDTO, "El DTO de préstamo no debería ser nulo");
		assertEquals(1, prestamoDTO.getIdPrestamo(), "El ID del préstamo no es el esperado");
		assertEquals(ETipos.LIBRO, prestamoDTO.getTipo(), "El tipo de préstamo no es el esperado");
	}

	@Test
	void testGetExceptionNoFoundPrestamo() {
		// Arrange
		when(prestamoRepository.findById(1)).thenReturn(Optional.empty());
		//Testear el exception asi que va salir error siempre
		// Act and Assert
		assertThrows(ExceptionNoFoundPrestamo.class, () -> prestamoService.get(1));
	}

	@Test
	void testCreate() throws NotFoundEjemplar, NotFoundSocio, NotFoundEmpleado, ExceptionNoFoundPrestamo {
		// Arrange
		PrestamoDTO prestamoDTO = new PrestamoDTO();
		prestamoDTO.setTipo(ETipos.REVISTA);
		prestamoDTO.setFechaPrestamo(OffsetDateTime.now());

		when(ejemplarRepository.findById(any())).thenReturn(Optional.of(new Ejemplar()));
		when(socioRepository.findById(any())).thenReturn(Optional.of(new Socio()));
		when(usuarioRepository.findById(any())).thenReturn(Optional.of(new Usuario()));

		when(prestamoRepository.save(any())).thenReturn(new Prestamo());
		//Error en el id es nulo por lo que no se puede usar correctacmente
		// Act
		Integer idPrestamo = prestamoService.create(prestamoDTO);

		// Assert
		assertNotNull(idPrestamo, "El ID del préstamo creado no debería ser nulo"+idPrestamo);
	}

	@Test
	void testUpdate() throws ExceptionNoFoundPrestamo, NotFoundEjemplar, NotFoundSocio, NotFoundEmpleado {
		// Arrange
		PrestamoDTO prestamoDTO = new PrestamoDTO();
		prestamoDTO.setTipo(ETipos.LIBRO);
		prestamoDTO.setFechaPrestamo(OffsetDateTime.now());

		Prestamo prestamo = new Prestamo();

		when(prestamoRepository.findById(1)).thenReturn(Optional.of(prestamo));
		when(ejemplarRepository.findById(any())).thenReturn(Optional.of(new Ejemplar()));
		when(socioRepository.findById(any())).thenReturn(Optional.of(new Socio()));
		when(usuarioRepository.findById(any())).thenReturn(Optional.of(new Usuario()));

		when(prestamoRepository.save(any())).thenReturn(new Prestamo());

		// Act
		prestamoService.update(1, prestamoDTO);

		// Assert
		// No exceptions should be thrown
	}

	@Test
	void testUpdateExceptionNoFoundPrestamo() {
		// Arrange
		PrestamoDTO prestamoDTO = new PrestamoDTO();

		when(prestamoRepository.findById(1)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(ExceptionNoFoundPrestamo.class, () -> prestamoService.update(1, prestamoDTO));
	}

	@Test
	void testDelete() {
		// Act
		prestamoService.delete(1);

		// Assert
		// No exceptions should be thrown
	}
}
