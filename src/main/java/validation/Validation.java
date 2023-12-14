package validation;

import equation.Equation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class check the entered equation for the correct placement of brackets.
 * Check the correctness of the entered expression (there should not be 2 signs of mathematical operations in a row,
 * for example, the expression 3+*4 is invalid, while the expression 4*-7 is valid).
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class Validation {

	/**
	 * The method checks the expression for validation (validation is provided by static methods).
	 *
	 * @param equation: String.
	 * @validateParenthesesPlacement.
	 * @validateExpression.
	 */
	public static String validateEquation(String equation) {
		validateParenthesesPlacement(equation);
		validateExpression(equation);
		return equation;
	}

	/**
	 * The method checks the placement of the parentheses; if the parentheses are set in the wrong order,
	 * an IllegalArgumentException is thrown.
	 * @param equation: String.
	 */
	private static void validateParenthesesPlacement(String equation) {
		int openParenthesisCount = 0;
		int closeParenthesisCount = 0;

		for (char c : equation.toCharArray()) {
			if (c == '(') {
				openParenthesisCount++;
			} else if (c == ')') {
				closeParenthesisCount++;

				if (closeParenthesisCount > openParenthesisCount) {
					throw new IllegalArgumentException("Incorrect placement of parentheses.");
				}
			}
		}

		if (openParenthesisCount != closeParenthesisCount) {
			throw new IllegalArgumentException("Incorrect placement of parentheses.");
		}
	}

	/**
	 * The method checks for the presence of two mathematical operators in a row; if they are found,
	 * an IllegalArgumentException is thrown.
	 *
	 * @param equation: String
	 */
	private static void validateExpression(String equation) {
		Pattern pattern = Pattern.compile(".*[+\\-*/]{2,}.*");
		Matcher matcher = pattern.matcher(equation);
		if (matcher.matches()) {
			throw new IllegalArgumentException("Incorrect expression (two mathematical operators in a row).");
		}
	}
}
