package io.bootify.libreri.errors.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FieldErrorTest {

	@Test
	void testGetField() {
		// Create a FieldError object
		FieldError fieldError = new FieldError();

		// Set a custom field name
		String expectedField = "fieldName";
		fieldError.setField(expectedField);

		// Test getter method
		String actualField = fieldError.getField();

		// Assert that the retrieved value matches the expected value
		assertEquals(expectedField, actualField, "getField() should return the set value");
	}

	@Test
	void testSetField() {
		// Create a FieldError object
		FieldError fieldError = new FieldError();

		// Set a new field name
		String newField = "updatedFieldName";
		fieldError.setField(newField);

		// Assert that the internal field is updated correctly
		assertEquals(newField, fieldError.getField(), "setField() should update the internal field");
	}

	@Test
	void testGetErrorCode() {
		// Create a FieldError object
		FieldError fieldError = new FieldError();

		// Set a custom error code
		String expectedErrorCode = "errorCode";
		fieldError.setErrorCode(expectedErrorCode);

		// Test getter method
		String actualErrorCode = fieldError.getErrorCode();

		// Assert that the retrieved value matches the expected value
		assertEquals(expectedErrorCode, actualErrorCode, "getErrorCode() should return the set value");
	}

	@Test
	void testSetErrorCode() {
		// Create a FieldError object
		FieldError fieldError = new FieldError();

		// Set a new error code
		String newErrorCode = "anotherErrorCode";
		fieldError.setErrorCode(newErrorCode);

		// Assert that the internal field is updated correctly
		assertEquals(newErrorCode, fieldError.getErrorCode(), "setErrorCode() should update the internal field");
	}
}
