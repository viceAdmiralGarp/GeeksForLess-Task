package dao.impl;

import equation.Equation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquationEntityDaoTest {

	private static final String TEST_EQUATION = "2 * x + 5";
	private static Long savedEquationId;

	@BeforeAll
	static void setUp() {
		// Insert a test equation into the database before running the tests
		EquationEntityDao equationDao = EquationEntityDao.getInstance();
		Equation testEquation = new Equation(TEST_EQUATION);
		equationDao.save(testEquation);
		savedEquationId = testEquation.getId();
	}

	@AfterAll
	static void tearDown() {
		// Delete the test equation from the database after running the tests
		EquationEntityDao equationDao = EquationEntityDao.getInstance();
		equationDao.delete(savedEquationId);
	}

	@Test
	void testSaveAndDeleteEquation() {
		EquationEntityDao equationDao = EquationEntityDao.getInstance();

		// Test Save Equation
		Equation equationToSave = new Equation("3 * x - 7");
		equationDao.save(equationToSave);
		Long savedId = equationToSave.getId();
		assertNotNull(savedId);

		// Ensure the equation is no longer present in the database
		assertFalse(equationDao.delete(savedId));
	}

	@Test
	void testSaveAndDeleteEquationWithId() {
		EquationEntityDao equationDao = EquationEntityDao.getInstance();

		// Test Save Equation with Specified ID
		Equation equationToSave = new Equation("4 * x + 2");
		Long specifiedId = 100L;
		equationToSave.setId(specifiedId);
		equationDao.save(equationToSave);

		// Ensure the specified ID is used
		assertEquals(specifiedId, equationToSave.getId());


		// Ensure the equation is no longer present in the database
		assertFalse(equationDao.delete(specifiedId));
	}
}