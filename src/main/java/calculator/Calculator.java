package calculator;

import java.util.Stack;

/**
 * Description: The Calculate class provides methods for performing calculations on mathematical equations.
 * It includes functionality to check if a given value is a root of an equation
 * and to calculate the result of an equation for a given variable.
 * <p>
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class Calculator {

	/**
	 * Description: Checks if two parts of an equation are approximately equal, indicating a possible root.
	 *
	 * @param firstPart  (double): The first part of the equation.
	 * @param secondPArt (double): The second part of the equation.
	 * @param root       (double): The value being evaluated for being a root
	 */
	public boolean equalPartsOfEquation(double firstPart, double secondPArt, double root) {
		if ((firstPart - secondPArt) < Math.pow(10, -9)) {
			System.out.println(root + ": root of equation");
			return true;
		} else {
			System.out.println(root + ":isn't a root of the equation ");
			return false;
		}
	}

	/**
	 * Description: Evaluates a mathematical equation for a given variable using the Shunting Yard algorithm.
	 *
	 * @param equation (String): The mathematical equation to be evaluated.
	 * @param x        (double): The value of the variable in the equation.
	 * @return (double):The result of the equation evaluation.
	 */
	public double calculateEquation(String equation, double x) {
		String[] components = breakDownEquation(equation);
		Stack<Double> operands = new Stack<>();
		Stack<Character> operators = new Stack<>();

		for (String component : components) {
			if (isNumeric(component)) {
				operands.push(Double.parseDouble(component));
			} else if (isVariable(component)) {
				operands.push(x);
			} else if (isOperator(component)) {
				while (!operators.isEmpty() && hasPrecedence(operators.peek(), component.charAt(0))) {
					applyOperation(operands, operators.pop());
				}
				operators.push(component.charAt(0));
			} else if (component.equals("(")) {
				operators.push('(');
			} else if (component.equals(")")) {
				while (!operators.isEmpty() && operators.peek() != '(') {
					applyOperation(operands, operators.pop());
				}
				operators.pop();
			}
		}
		while (operands.size() != 1 && !operators.isEmpty()) {
			applyOperation(operands, operators.pop());
		}
		return operands.pop();
	}

	/**
	 * Description: Applies a mathematical operation to two operands.
	 *
	 * @param operands        (Stack<Double>): Stack containing operands.
	 * @param operator(char): The operator to be applied.
	 *                        Output: Modifies the stack by applying the specified operation.
	 */

	public static void applyOperation(Stack<Double> operands, char operator) {
		double operand2 = operands.pop();
		double operand1 = operands.pop();

		switch (operator) {
			case '+':
				operands.push(operand1 + operand2);
				break;
			case '-':
				operands.push(operand1 - operand2);
				break;
			case '*':
				operands.push(operand1 * operand2);
				break;
			case '/':
				operands.push(operand1 / operand2);
				break;
		}
	}

	/**
	 * Description: Determines if one operator has higher precedence than another.
	 *
	 * @param op1 (char): The first operator.
	 * @param op2 (char): The second operator.
	 * @return (boolean):Returns true if op1 has higher precedence than op2, false otherwise.
	 */
	public static boolean hasPrecedence(char op1, char op2) {
		return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
	}

	/**
	 * Description: Breaks down a mathematical equation into its components (numbers, operators, variables).
	 *
	 * @param equation (String): The mathematical equation to be processed.
	 * @return (String[]):  Returns an array of components.
	 */
	public static String[] breakDownEquation(String equation) {
		// Replace all spaces to make it easier to process
		equation = equation.replaceAll("\\s", "");

		// Break the equation into components (numbers, operators, variables)
		return equation.split("(?=[-+*/=()])|(?<=[-+*/=()])");
	}

	/**
	 * Description: Checks if a given string represents a numeric value.
	 *
	 * @param str (String): The string to be checked.
	 * @return (boolean) Returns true if the string is numeric, false otherwise.
	 */
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	/**
	 * Description: Checks if a given string represents a variable.
	 *
	 * @param str (String): The string to be checked.
	 * @return (boolean): Returns true if the string is a variable, false otherwise
	 */
	public static boolean isVariable(String str) {
		return str.matches("[a-zA-Z]+");
	}

	/**
	 * Description: Checks if a given string represents a mathematical operator.
	 *
	 * @param str (String): The string to be checked.
	 * @return (boolean): Returns true if the string is an operator, false otherwise.
	 */
	public static boolean isOperator(String str) {
		return str.matches("[-+*/=]");
	}
}
