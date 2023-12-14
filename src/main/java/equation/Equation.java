package equation;

/**
 * Description:The Equation class is designed to store the equation itself
 * in the form of a string and a root of this equation.
 * <p>
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class Equation {

	/**
	 *
	 */
	private long id;

	/**
	 * Description:The value store String (equation).
	 */
	private String equation;
	/**
	 * Description:The value store double (root of the equation)
	 */
	private double root;

	/**
	 * Description:Initialization of equation and root
	 *
	 * @param equation String (equation)
	 * @param root     double (root)
	 */
	public Equation(String equation, double root) {
		this.equation = equation;
		this.root = root;
	}

	public Equation(String equation) {
		this.equation = equation;
	}

	/**
	 * @return String (equation)
	 */
	public String getEquation() {
		return equation;
	}

	/**
	 * @return The root of the equation in double format
	 */
	public double getRoot() {
		return root;
	}

	public void setEquation(String expression) {
		this.equation = expression;
	}

	public void setRoot(double root) {
		this.root = root;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}