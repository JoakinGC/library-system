package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundEmpleadoTest {

	@Test
	void testNotFoundEmpleado() {
		// Act
		NotFoundEmpleado exception = new NotFoundEmpleado();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertEquals("Empleado NO encontrado", exception.getMessage(), "El mensaje no es el esperado");
		assertNull(exception.getCause(), "La causa debería ser nula");
	}

	@Test
	void testNotFoundEmpleadoStringThrowable() {
		// Arrange
		String message = "Mensaje de prueba";
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		NotFoundEmpleado exception = new NotFoundEmpleado(message, cause);

		// Assert
		assertNotNull(exception, "El constructor con mensaje y causa no funciona correctamente");
		assertEquals(message, exception.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}

	@Test
	void testNotFoundEmpleadoThrowable() {
		// Arrange
		Throwable cause = new Throwable("Causa de prueba");

		// Act
		NotFoundEmpleado exception = new NotFoundEmpleado(cause);
		NotFoundEmpleado exception2 = new NotFoundEmpleado();

		// Assert
		assertNotNull(exception, "El constructor con causa no funciona correctamente");
		assertEquals("Empleado NO encontrado", exception2.getMessage(), "El mensaje no es el esperado");
		assertEquals(cause, exception.getCause(), "La causa no es la esperada");
	}
}
