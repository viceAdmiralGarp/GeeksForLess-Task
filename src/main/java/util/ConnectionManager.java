package util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description: The ConnectionManager class provides a connection pooling mechanism for managing database connections
 * efficiently.It uses a blocking queue (BlockingQueue) to create a pool of reusable connections.Each connection
 * is wrapped with a dynamic proxy to intercept the close method, allowing the connection to be returned to
 * the pool instead of being closed.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public final class ConnectionManager {
	/**
	 * Description:  Key for the database URL in the properties file.
	 */
	private static final String URL_KEY = "db.url";

	/**
	 * Description:Key for the database username in the properties file.
	 */
	private static final String NAME_KEY = "db.username";

	/**
	 * Description:Key for the database password in the properties file.
	 */
	private static final String PASSWORD_KEY = "db.password";

	/**
	 * Description: Key for the pool size in the properties file.
	 */
	private static final String POOL_SIZE_KEY = "db.pool.size";

	/**
	 * Description:  Default pool size if not specified in the properties file.
	 */
	private static final Integer DEFAULT_POOL_SIZE = 10;

	/**
	 * Description:  A blocking queue to store reusable connections.
	 */
	private static BlockingQueue<Connection> pool;

	/**
	 * Description: A list to keep track of original connections for closing.
	 */
	static List<Connection> sourceConnections;

	static {
		initConnectionPool();
	}

	private ConnectionManager() {
	}

	/**
	 * Description: Initializes the connection pool by creating and proxying connections based on the
	 * specified pool size.
	 */
	private static void initConnectionPool() {
		var poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
		var size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
		pool = new ArrayBlockingQueue<>(size);
		sourceConnections = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			var connection = open();
			var proxyConnection = (Connection)
					Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
							new Class[]{Connection.class},
							(proxy, method, args) -> method.getName().equals("close")
									? pool.add((Connection) proxy)
									: method.invoke(connection, args));
			pool.add(proxyConnection);
			sourceConnections.add(connection);
		}
	}

	/**
	 * Description: Retrieves a connection from the connection pool. Blocks if no connections are available.
	 * @return (BlockingQueue<Connection>):  Returns a database connection.
	 */
	public static Connection get() {
		try {
			return pool.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *  Description: Opens a new database connection.
	 *
	 * @return (DriverManager): Returns a new database connection.
	 */
	private static Connection open() {
		try {
			return DriverManager.getConnection(
					PropertiesUtil.get(URL_KEY),
					PropertiesUtil.get(NAME_KEY),
					PropertiesUtil.get(PASSWORD_KEY));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Description: Closes all source connections in the connection pool.
	 */
	public static void closePool() {

		try {
			for (Connection sourceConnection : sourceConnections) {
				sourceConnection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
