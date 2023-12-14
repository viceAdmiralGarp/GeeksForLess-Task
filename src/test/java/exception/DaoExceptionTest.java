package exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DaoExceptionTest {

	@Test
	void testDaoExceptionConstruction() {
		Throwable cause = new RuntimeException("Test cause");
		DaoException daoException = new DaoException(cause);

		assertEquals(cause, daoException.getCause());
		assertEquals("Test cause", daoException.getCause().getMessage());
	}
}