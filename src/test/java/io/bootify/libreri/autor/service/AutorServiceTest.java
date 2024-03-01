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
	void testFindAll() {

		// Calling the actual method
		List<AutorDTO> result = autorService.findAll();

		// Assertions
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testGet() {


		// Calling the actual method
		AutorDTO result = autorService.get(1);

		// Assertions
		assertNotNull(result);
	}

	@Test
	void testCreate() {


		// Calling the actual method
		Integer result = autorService.create(new AutorDTO());

		// Assertions
		assertNotNull(result);
	}

	@Test
	void testUpdate() {


		// Calling the actual method
		autorService.update(1, new AutorDTO());
		AutorDTO autor = autorService.get(1);


		if (autor.getNombre() == null||autor.getNombre().isEmpty()){

		}else{
			fail("NO funciona la actulizacion");
		}

	}

	@Test
	void testDelete() {
		// Mocking the AutorRepository behavior


		// Calling the actual method
		autorService.delete(1);
	}

	@Test
	void testGetReferencedWarning() {
		// Mocking the AutorRepository behavior
		Mockito.when(autorRepository.findById(Mockito.any(Integer.class))).thenReturn(java.util.Optional.of(new Autor()));

		// Mocking the LibrosRepository behavior
		Mockito.when(librosRepository.findFirstByLibroAutorAutors(Mockito.any(Autor.class))).thenReturn(new Libros());

		// Calling the actual method
		String result = autorService.getReferencedWarning(1);

		// Assertions
		assertNotNull(result);
	}
}