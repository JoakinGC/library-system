package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundSocioTest {

	@Test
	void testNotFoundSocioString() {
		// Act
		NotFoundSocio exception = new NotFoundSocio();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertEquals("Socico NO encontrado", exception.getMessage(), "El mensaje no es el esperado");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testNotFoundSocioStringThrowable() {
		// Arrange
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		NotFoundSocio exception = new NotFoundSocio("Socico NO encontrado", cause);

		// Assert
		assertNotNull(exception, "El constructor con mensaje y causa no funciona correctamente");
		assertEquals("Socico NO encontrado", exception.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}

	@Test
	void testNotFoundSocioThrowable() {
		// Arrange
		Throwable cause = new Throwable("Socico NO encontrado");

		// Act
		NotFoundSocio exception = new NotFoundSocio(cause);

		// Assert
		assertNotNull(exception, "El constructor con causa no funciona correctamente");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}
}