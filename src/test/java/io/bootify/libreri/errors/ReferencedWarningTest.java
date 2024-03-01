package io.bootify.libreri.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ReferencedWarningTest {

	@Test
	void testAddParam() {
		ReferencedWarning warning = new ReferencedWarning();

		// Add two different parameters
		String param1 = "Value 1";
		int param2 = 2;
		warning.addParam(param1);
		warning.addParam(param2);

		// Assert that the parameters are added to the list
		assertEquals(2, warning.getParams().size(), "Params list should contain two elements");
		assertEquals(param1, warning.getParams().get(0), "First parameter should be 'Value 1'");
		assertEquals(param2, warning.getParams().get(1), "Second parameter should be 2 (integer)");
	}

	@Test
	void testToMessage() {
		// Arrange
		ReferencedWarning warning = new ReferencedWarning();
		warning.setKey("testKey");
		warning.addParam("param1");
		warning.addParam("param2");

		// Act
		String message = warning.toMessage();

		// Assert
		assertEquals("testKey,param1,param2", message, "El mensaje no es el esperado");
	}

	@Test
	void testToMessageWithoutParams() {
		ReferencedWarning warning = new ReferencedWarning();
		warning.setKey("Key1");

		// Assert that the message is just the key
		assertEquals("Key1", warning.toMessage(), "Message should be just the key without params");
	}

	@Test
	void testToMessageWithParams() {
		ReferencedWarning warning = new ReferencedWarning();
		warning.setKey("Key2");
		warning.addParam("Param 1");
		warning.addParam("Param 2");

		// Assert that the message includes the key and formatted params
		assertEquals("Key2,Param 1,Param 2", warning.toMessage(),
				"Message should include key and comma-separated params");
	}

	@Test
	void testGetKey() {
		ReferencedWarning warning = new ReferencedWarning();
		warning.setKey("Test Key");

		// Assert that the key is retrieved correctly
		assertEquals("Test Key", warning.getKey(), "getKey() should return the set key");
	}

	@Test
	void testSetKey() {
		ReferencedWarning warning = new ReferencedWarning();
		warning.setKey("Initial Key");

		// Set a new key and verify the change
		String newKey = "Updated Key";
		warning.setKey(newKey);
		assertEquals(newKey, warning.getKey(), "setKey() should update the key");
	}

	@Test
	void testGetParams() {
		ReferencedWarning warning = new ReferencedWarning();
		warning.addParam("Param A");
		warning.addParam("Param B");

		var params = warning.getParams();

		// Assert that the returned list is the same as the internal one
		assertSame(warning.getParams(), warning.getParams(),
				"getParams() should return the same reference as the internal list");
		assertTrue(params.contains("Param A") && params.contains("Param B"), "Los parámetros no son los esperados");
	}

	@Test
	void testSetParams() {
		ReferencedWarning warning = new ReferencedWarning();
		warning.addParam("Param 1");

		// Prepare a new list of parameters
		ArrayList<Object> newParams = new ArrayList<>();
		newParams.add("Param 2");
		newParams.add("Param 3");

		// Set the new list and verify its contents
		warning.setParams(newParams);
		assertEquals(2, warning.getParams().size(), "setParams() should update the list size");
		assertEquals("Param 2", warning.getParams().get(0), "First param should be updated");
		assertEquals("Param 3", warning.getParams().get(1), "Second param should be added");
	}


}
