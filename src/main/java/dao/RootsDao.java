package dao;

import equation.Equation;

/**
 * Description: This interface defines the contract for Data Access Objects (DAOs) that handle operations
 * related to the Roots entity in the database.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public interface RootsDao {

	/**
	 * Description: Saves the provided Equation in the database.
	 *
	 * @param equation The Equation object to be saved.
	 */
	void save(Equation equation);

	/**
	 * Description: Deletes the Roots record with the specified ID from the database.
	 *
	 * @param id The ID of the Roots record to be deleted.
	 * @return true if the deletion is successful, false otherwise.
	 */
	boolean delete(Long id);

	/**
	 * Description: Finds and prints all equations associated with the specified root.
	 *
	 * @param equation The Equation object containing the root value for which
	 *                 associated equations should be retrieved and printed.
	 */
	void findEquationsByRoot(Equation equation);
}
