package rootsubstitution.impl;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SubstitutingRootInAnEquationTest {

	@Test
	void testRootSubstitution() {
		SubstitutingRootInAnEquation substitutor = new SubstitutingRootInAnEquation();
		String[] parts = {"2x", "+", "5"};
		List<String> result = substitutor.rootSubstitution(parts, 3.0);

		assertEquals(3, result.size());
		assertEquals("23.0", result.get(0)); // 2 * 3
		assertEquals("+", result.get(1));
		assertEquals("5", result.get(2));
	}

	@Test
	void testSplitEquation() {
		SubstitutingRootInAnEquation substitutor = new SubstitutingRootInAnEquation();
		String equation = "2x+5=10-3x";
		String[] result = substitutor.splitEquation(equation);

		assertEquals(2, result.length);
		assertEquals("2x+5", result[0]);
		assertEquals("10-3x", result[1]);
	}
}
