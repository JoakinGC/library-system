package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReferencedExceptionTest {

	@Test
	void testReferencedException() {
		// Act
		ReferencedException exception = new ReferencedException();

		// Assert
		assertNotNull(exception, "El constructor por defecto no funciona correctamente");
		assertNull(exception.getMessage(), "El mensaje debería ser nulo");
	}

	@Test
	void testReferencedExceptionReferencedWarning() {
		// Arrange
		ReferencedWarning referencedWarning = new ReferencedWarning();

		// Act
		ReferencedException exception = new ReferencedException(referencedWarning);

		// Assert
		assertNotNull(exception, "El constructor con ReferencedWarning no funciona correctamente");
		assertEquals(referencedWarning.toMessage(), exception.getMessage(), "El mensaje no es el esperado");
	}
}
