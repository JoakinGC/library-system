package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class EmptyParametrerTest {

	@Test
	void testEmptyParametrerString() {
		// Arrange
		String parametro = "Parametro de test";

		// Act
		EmptyParametrer e = new EmptyParametrer(parametro);

		// Assert
		assertNotNull(e, "El constructor no funciona correctamente");
		assertFalse(e.getMessage().isEmpty(), "No se guarda correctamente el parámetro");
		assertEquals(parametro + "   esta VACIO", e.getMessage(), "El mensaje no es el esperado");
	}

	@Test
	void testEmptyParametrerStringThrowable() {
		// Arrange
		Throwable throwable = new Throwable("Test PArametro Error");

		// Act
		EmptyParametrer cause = new EmptyParametrer("Mensaje de error", throwable);

		// Assert
		assertNotNull(cause.getCause(), "No se implementó correctamente el throw");
		assertFalse(cause.getMessage().isEmpty(), "Fallo en la implementación del mensaje");
	}

	@Test
	void testEmptyParametrerThrowable() {
		// Act
		EmptyParametrer e = new EmptyParametrer(new Throwable("Test Throwable"));

		// Assert
		assertNotNull(e, "El constructor no funciona correctamente");
		assertNotNull(e.getCause(), "No se implementó correctamente el throw");
		assertEquals("Test Throwable", e.getCause().getMessage(), "El mensaje de la causa no es el esperado");
	}

	@Test
	void testGetMessage() {
		// Arrange
		String parametro = "Test Parametro";

		// Act
		EmptyParametrer e = new EmptyParametrer(parametro);

		// Assert
		assertEquals(parametro + "   esta VACIO", e.getMessage(), "El mensaje no es el esperado");
	}
}

