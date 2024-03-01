package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsernameNotFoundExceptionTest {

	@Test
	void testUsernameNotFoundException() {
		// Act
		UsernameNotFoundException exception = new UsernameNotFoundException();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertNull(exception.getMessage(), "El mensaje debería ser nulo");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testUsernameNotFoundExceptionString() {
		// Arrange
		String message = "Mensaje de prueba";

		// Act
		UsernameNotFoundException exception = new UsernameNotFoundException(message);

		// Assert
		assertNotNull(exception, "El constructor con mensaje no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testUsernameNotFoundExceptionStringThrowable() {
		// Arrange
		String message = "Mensaje de prueba";
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		UsernameNotFoundException exception = new UsernameNotFoundException(message, cause);

		// Assert
		assertNotNull(exception, "El constructor con mensaje y causa no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}

	@Test
	void testUsernameNotFoundExceptionThrowable() {
		// Arrange
		Throwable cause = new Throwable("Causa de prueba");


		// Act
		UsernameNotFoundException exception = new UsernameNotFoundException(cause);


		// Assert
		assertNotNull(exception, "El constructor con causa no funciona correctamente");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}
}
