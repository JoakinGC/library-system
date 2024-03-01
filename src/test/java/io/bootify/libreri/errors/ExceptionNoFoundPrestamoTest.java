package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExceptionNoFoundPrestamoTest {

	@Test
	void testExceptionNoFoundPrestamo() {
		// Act
		ExceptionNoFoundPrestamo exception = new ExceptionNoFoundPrestamo();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertNull(exception.getMessage(), "El mensaje debería ser nulo");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testExceptionNoFoundPrestamoString() {
		// Arrange
		String message = "Mensaje de prueba";

		// Act
		ExceptionNoFoundPrestamo exception = new ExceptionNoFoundPrestamo(message);

		// Assert
		assertNotNull(exception, "El constructor con mensaje no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testExceptionNoFoundPrestamoStringThrowable() {
		// Arrange
		String message = "Mensaje de prueba";
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		ExceptionNoFoundPrestamo exception = new ExceptionNoFoundPrestamo(message, cause);

		// Assert
		assertNotNull(exception, "El constructor con mensaje y causa no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}

	@Test
	void testExceptionNoFoundPrestamoThrowable() {
		// Arrange
		Throwable cause = new Throwable("Causa de prueba");
		Throwable cause2 = new Throwable();

		// Act
		ExceptionNoFoundPrestamo exception = new ExceptionNoFoundPrestamo(cause);
		ExceptionNoFoundPrestamo exception2 = new ExceptionNoFoundPrestamo(cause2);

		// Assert
		assertNotNull(exception, "El constructor con causa no funciona correctamente");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}
}
