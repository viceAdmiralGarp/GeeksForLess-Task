package calculator;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

	@Test
	public void testEqualPartsOfEquation() {
		Calculator calculator = new Calculator();
		assertTrue(calculator.equalPartsOfEquation(5.0, 5.0, 5.0));
		assertFalse(calculator.equalPartsOfEquation(5.0, 4.0, 5.0));
	}

	@Test
	public void testCalculateEquation() {
		Calculator calculator = new Calculator();
		assertEquals(10.0, calculator.calculateEquation("5 + 5", 0.0), 0.000001);
		assertEquals(25.0, calculator.calculateEquation("5 * 5", 0.0), 0.000001);
		assertEquals(1.0, calculator.calculateEquation("5 / 5", 0.0), 0.000001);
		assertEquals(0.0, calculator.calculateEquation("5 - 5", 0.0), 0.000001);
	}

	@Test
	public void testApplyOperation() {
		Calculator calculator = new Calculator();
		Stack<Double> operands = new Stack<>();
		operands.push(2.0);
		operands.push(3.0);
		calculator.applyOperation(operands, '+');
		assertEquals(5.0, operands.pop(), 0.000001);
	}

	@Test
	public void testHasPrecedence() {
		Calculator calculator = new Calculator();
		assertTrue(calculator.hasPrecedence('*', '+'));
		assertFalse(calculator.hasPrecedence('+', '*'));
		assertFalse(calculator.hasPrecedence('+', '-'));
	}

	@Test
	public void testBreakDownEquation() {
		Calculator calculator = new Calculator();
		String[] components = calculator.breakDownEquation("5 + 2 * x");
		assertArrayEquals(new String[]{"5", "+", "2", "*", "x"}, components);
	}

	@Test
	public void testIsNumeric() {
		Calculator calculator = new Calculator();
		assertTrue(calculator.isNumeric("123"));
		assertTrue(calculator.isNumeric("-456.789"));
		assertFalse(calculator.isNumeric("abc"));
	}

	@Test
	public void testIsVariable() {
		Calculator calculator = new Calculator();
		assertTrue(calculator.isVariable("x"));
		assertTrue(calculator.isVariable("variable"));
		assertFalse(calculator.isVariable("123"));
	}

	@Test
	public void testIsOperator() {
		Calculator calculator = new Calculator();
		assertTrue(calculator.isOperator("+"));
		assertTrue(calculator.isOperator("-"));
		assertTrue(calculator.isOperator("*"));
		assertTrue(calculator.isOperator("/"));
		assertTrue(calculator.isOperator("="));
		assertFalse(calculator.isOperator("x"));
	}
}
