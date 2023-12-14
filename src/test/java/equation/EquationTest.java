package equation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class EquationTest {

	@Test
	void testEquationInitialization() {
		Equation equation = new Equation("x^2 - 4", 2.0);

		assertEquals("x^2 - 4", equation.getEquation());
		assertEquals(2.0, equation.getRoot(), 0.0001);
	}

	@Test
	void testEquationSetters() {
		Equation equation = new Equation("x^2 - 4");
		equation.setEquation("2x - 8");
		equation.setRoot(4.0);

		assertEquals("2x - 8", equation.getEquation());
		assertEquals(4.0, equation.getRoot(), 0.0001);
	}

	@Test
	void testEquationId() {
		Equation equation = new Equation("x^2 - 4");
		equation.setId(1L);

		assertEquals(1L, equation.getId());
	}
}
