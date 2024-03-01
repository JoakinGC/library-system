package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;

class NotFoundEjemplarTest {

	@Test
	void testNotFoundEjemplar() {
		// Act
		NotFoundEjemplar exception = new NotFoundEjemplar();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertEquals("Ejemplar NO encontrado", exception.getMessage(), "El mensaje no es el esperado");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testNotFoundEjemplarStringThrowable() {
		// Arrange
		String message = "Mensaje de prueba";
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		NotFoundEjemplar exception = new NotFoundEjemplar(message, cause);

		// Assert
		assertNotNull(exception, "El constructor con mensaje y causa no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}

	@Test
	void testNotFoundEjemplarThrowable() {
		// Arrange
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		NotFoundEjemplar exception = new NotFoundEjemplar(cause);
		NotFoundEjemplar exception2 = new NotFoundEjemplar();

		// Assert
		assertNotNull(exception, "El constructor con causa no funciona correctamente");
		assertEquals("Ejemplar NO encontrado", exception2.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}
}

