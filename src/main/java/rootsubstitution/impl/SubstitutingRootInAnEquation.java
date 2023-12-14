package rootsubstitution.impl;

import rootsubstitution.SplitEquation;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:The SubstitutingRootInAnEquation class implements the SplitEquation interface and provides methods for
 * substituting a root value into the components of a mathematical equation. It also includes a method to
 * split an equation into its left and right parts.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class SubstitutingRootInAnEquation implements SplitEquation {

	/**
	 * Description:A list to store the components of the equation after root substitution.
	 */
	List<String> partOfEquation = new ArrayList<>();

	/**
	 * Description: Returns the list of equation components after root substitution.
	 *
	 * @return (List ( String)): Returns a List of Strings representing the components of
	 * the equation after root substitution.
	 */
	public List<String> getPartOfEquation() {
		return partOfEquation;
	}

	/**
	 * Description: Substitutes a root value into the components of a mathematical equation.
	 *
	 * @param partsOfEquation (String[]): Array of strings representing the components of the equation.
	 * @param root            (double): The value to be substituted for the variable 'x'.
	 * @return (List ( String)):Returns a List of Strings representing the components
	 * f the equation after root substitution.
	 */
	public List<String> rootSubstitution(String[] partsOfEquation, double root) {
		for (String s : partsOfEquation) {
			var chars = s.split("");
			for (int i = 0; i < chars.length; i++) {
				if (chars[i].equals("x")) {
					chars[i] = Double.toString(root);
				}
			}
			this.partOfEquation.add(String.join("", chars));
		}
		return partOfEquation;
	}

	/**
	 * Description:The method to divide an equation into 2 parts on either side of the "=".
	 * Example: input -> 2x+5=10-3x
	 * Output -> [2x+5], [10-3x].
	 *
	 * @param equation (String)
	 * @return (String[])
	 */
	@Override
	public String[] splitEquation(String equation) {
		var partsOfEquation = equation.split("=");
		return partsOfEquation;
	}
}
