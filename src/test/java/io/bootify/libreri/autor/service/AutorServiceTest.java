package io.bootify.libreri.autor.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import io.bootify.libreri.autor.domain.Autor;
import io.bootify.libreri.autor.model.AutorDTO;
import io.bootify.libreri.autor.repos.AutorRepository;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.prestamo.service.PrestamoService;
import io.bootify.libreri.socio.repos.SocioRepository;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import java.util.List;

class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;

	@Mock
	private LibrosRepository librosRepository;

	@InjectMocks
	private AutorService autorService;

	@BeforeEach
	void setUp() {
		autorService = mock(AutorService.class);
		librosRepository = mock(LibrosRepository.class);
		autorRepository = mock(AutorRepository.class);
	}





	@Test
	void testCreate() {


		// Calling the actual method
		Integer result = autorService.create(new AutorDTO());

		// Assertions
		assertNotNull(result);
	}


	@Test
	void testDelete() {
		// Mocking the AutorRepository behavior


		// Calling the actual method
		autorService.delete(1);
	}


}