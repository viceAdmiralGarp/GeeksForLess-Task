package util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static util.ConnectionManager.sourceConnections;

public class ConnectionManagerTest {
	private static final String TEST_DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String TEST_DB_USERNAME = "postgres";
	private static final String TEST_DB_PASSWORD = "postgres";

	@BeforeAll
	static void setUp() {
		// Set up the properties for the test database
		System.setProperty("db.url", TEST_DB_URL);
		System.setProperty("db.username", TEST_DB_USERNAME);
		System.setProperty("db.password", TEST_DB_PASSWORD);
		System.setProperty("db.pool.size", "5");
	}

	@Test
	void testClosePool() {
		// Close the entire pool and check if connections are closed
		ConnectionManager.closePool();

		// Try to get a connection, and it should be a new one (not the one in the pool)
		Connection newConnection = ConnectionManager.get();
		assertNotEquals(sourceConnections.get(0), newConnection);
	}
}
