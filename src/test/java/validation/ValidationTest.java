package validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTest {

	@Test
	void testValidEquation() {
		// Valid equation with correct parentheses and expression
		String validEquation = "3*(4+5)-2";
		assertDoesNotThrow(() -> Validation.validateEquation(validEquation));
	}

	@Test
	void testInvalidParenthesesPlacement() {
		// Invalid equation with incorrect parentheses placement
		String invalidEquation = "3*(4+5-2";
		assertThrows(IllegalArgumentException.class, () -> Validation.validateEquation(invalidEquation));
	}

	@Test
	void testInvalidExpression() {
		// Invalid equation with two mathematical operators in a row
		String invalidEquation = "3++4";
		assertThrows(IllegalArgumentException.class, () -> Validation.validateEquation(invalidEquation));
	}

	@Test
	void testValidExpressionWithMultipleOperators() {
		// Valid equation with multiple operators
		String validEquation = "4+2*3-1";
		assertDoesNotThrow(() -> Validation.validateEquation(validEquation));
	}
}