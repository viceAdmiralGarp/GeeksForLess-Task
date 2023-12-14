import calculator.Calculator;
import dao.impl.EquationEntityDao;
import dao.impl.RootsEntityDao;
import equation.Equation;
import rootsubstitution.impl.SubstitutingRootInAnEquation;
import validation.Validation;


/**
 * Class for launching "Math Assistant"
 */
public class Main {
	public static void main(String[] args) {

		//creating an equation with roots
		Equation equation = new Equation("2*5*3=10", 1);
		System.out.println(equation.getEquation() + " " + equation.getRoot());

		//checking for correctness of parentheses
		//(there should not be 2 signs of mathematical operations in a row, for example, the expression 3+*4 is invalid,
		// while the expression 4*-7 is valid).

		//CASES OF TYPE 4*-7 ARE Not IMPLEMENTED
		Validation validationOfEquation = new Validation();
		var validationEquation = validationOfEquation.validateEquation(equation.getEquation());

		//If the equation is correct, we save it in the database.
		EquationEntityDao equationEntityDao = EquationEntityDao.getInstance();
		var eqv = new Equation(validationEquation);
		equationEntityDao.save(eqv);

		//dividing the equation into 2 parts and substituting the root
		SubstitutingRootInAnEquation substitutingRootInAnEquation = new SubstitutingRootInAnEquation();
		var splitEquation = substitutingRootInAnEquation.splitEquation(validationEquation);
		var partsOfEquation = substitutingRootInAnEquation.rootSubstitution(splitEquation, equation.getRoot());

		//calculating equation
		Calculator calculator = new Calculator();
		var firstPart = calculator.calculateEquation(partsOfEquation.get(0), equation.getRoot());
		var secondPart = calculator.calculateEquation(partsOfEquation.get(1), equation.getRoot());
		System.out.println(firstPart+" = "+secondPart);
		var root = calculator.equalPartsOfEquation(firstPart, secondPart, equation.getRoot());

		//if the root is less than 10^-9 then it is stored in the database
		RootsEntityDao rootsEntityDao = RootsEntityDao.getInstance();
		if(root==true){
			rootsEntityDao.save(equation);
		}

		//Implementation of the function of searching for equations in the database by their roots. For example,
		// a possible query: find all equations that have one of the specified roots or find all equations that have
		// exactly one root stored in the database.
		rootsEntityDao.findEquationsByRoot(equation);
	}
}
