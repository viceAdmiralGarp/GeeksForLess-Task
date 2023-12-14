package util;

import java.io.IOException;
import java.util.Properties;

/**
 * Description: The PropertiesUtil class provides utility methods for accessing properties from a configuration file
 * named "application.properties." It uses a Properties object to store and retrieve key-value pairs from the
 * configuration file.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public final class PropertiesUtil {

	/**
	 * Description: A static Properties object to store configuration properties.
	 */
	private final static Properties PROPERTIES = new Properties();

	static {
		loadProperties();
	}

	private PropertiesUtil() {
	}

	/**
	 * Description: Retrieves the value associated with the specified key from the configuration properties.
	 *
	 * @param key (String): The key for which the associated value is to be retrieved.
	 * @return (Properties): Returns the value associated with the specified key, or null if the key is not found.
	 */
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}

	/**
	 * Description: Loads the configuration properties from the "application.properties"
	 * file into the PROPERTIES object.
	 */
	private static void loadProperties() {
		try (var inputStream = PropertiesUtil.class
				.getClassLoader()
				.getResourceAsStream("application.properties")) {
			PROPERTIES.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
