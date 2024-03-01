package io.bootify.libreri.errors.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

class ErrorResponseTest {

	@Test
	void testGetHttpStatus() {
		// Arrange
		ErrorResponse response = new ErrorResponse();
		response.setHttpStatus(404);

		// Act
		Integer httpStatus = response.getHttpStatus();

		// Assert
		assertEquals(404, httpStatus, "El HttpStatus no es el esperado");
	}

	@Test
	void testSetHttpStatus() {
		// Arrange
		ErrorResponse response = new ErrorResponse();

		// Act
		response.setHttpStatus(500);

		// Assert
		assertEquals(500, response.getHttpStatus(), "El HttpStatus no se estableció correctamente");
	}

	@Test
	void testGetException() {
		// Arrange
		ErrorResponse response = new ErrorResponse();
		response.setException("TestException");

		// Act
		String exception = response.getException();

		// Assert
		assertEquals("TestException", exception, "La excepción no es la esperada");
	}

	@Test
	void testSetException() {
		// Arrange
		ErrorResponse response = new ErrorResponse();

		// Act
		response.setException("NewException");

		// Assert
		assertEquals("NewException", response.getException(), "La excepción no se estableció correctamente");
	}

	@Test
	void testGetMessage() {
		// Arrange
		ErrorResponse response = new ErrorResponse();
		response.setMessage("TestMessage");

		// Act
		String message = response.getMessage();

		// Assert
		assertEquals("TestMessage", message, "El mensaje no es el esperado");
	}

	@Test
	void testSetMessage() {
		// Arrange
		ErrorResponse response = new ErrorResponse();

		// Act
		response.setMessage("NewMessage");

		// Assert
		assertEquals("NewMessage", response.getMessage(), "El mensaje no se estableció correctamente");
	}

	@Test
	void testGetFieldErrors() {
		// Arrange

		ErrorResponse response = new ErrorResponse();
		FieldError fieldError = new FieldError();

		// Act
		response.setFieldErrors(List.of(fieldError));

		// Assert
		assertTrue(response.getFieldErrors().contains(fieldError), "Los errores de campo no son los esperados");
	}

	@Test
	void testSetFieldErrors() {
		// Arrange

		ErrorResponse response = new ErrorResponse();
		FieldError fieldError = new FieldError();

		// Act
		response.setFieldErrors(List.of(fieldError));

		// Assert
		assertTrue(response.getFieldErrors().contains(fieldError), "Los errores de campo no se establecieron correctamente");
	}
}
