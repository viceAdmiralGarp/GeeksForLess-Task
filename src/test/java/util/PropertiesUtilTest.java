package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertiesUtilTest {

	@Test
	void testGetProperty() {
		String value = PropertiesUtil.get("db.url");
		assertNotNull(value);
		assertEquals("jdbc:postgresql://localhost:5432/postgres", value);
	}

	@Test
	void testPoolSizeIsNotNull() {
		String value = PropertiesUtil.get("db.pool.size");
		assertNotNull(value);
	}

	@Test
	void testGetPasswordIsNotNull() {
		String value = PropertiesUtil.get("db.password");
		assertNotNull(value);
	}

	@Test
	void testLoadProperties() {
		String value = PropertiesUtil.get("db.username");
		assertNotNull(value);
	}
}
