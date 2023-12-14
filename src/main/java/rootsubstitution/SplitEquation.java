package rootsubstitution;

/**
 * Description:The SplitEquation interface defines a contract for classes that implement the functionality to split
 * a mathematical equation into its parts. It includes a single method for this purpose.
 */
public interface SplitEquation {

	/**
	 * Description: Splits a mathematical equation into its components based on a specified delimiter.
	 *
	 * @param str (String): The mathematical equation to be split.
	 * @return (String[]): Returns an array of strings representing the components of the equation.
	 */
	String[] splitEquation(String str);
}
