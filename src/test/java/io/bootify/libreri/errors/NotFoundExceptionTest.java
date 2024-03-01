package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

	@Test
	void testNotFoundException() {
		// Act
		NotFoundException exception = new NotFoundException();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertNull(exception.getMessage(), "El mensaje debería ser nulo");
	}

	@Test
	void testNotFoundExceptionString() {
		// Arrange
		String message = "Mensaje de prueba";

		// Act
		NotFoundException exception = new NotFoundException(message);

		// Assert
		assertNotNull(exception, "El constructor con mensaje no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
	}
}

