package dao;

import equation.Equation;

/**
 * Description: This interface defines the contract for Data Access Objects (DAOs) that handle operations
 * related to the Equation entity in the database.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public interface EquationDao {

	/**
	 * Description: Saves the provided Equation in the database.
	 *
	 * @param equation The Equation object to be saved.
	 */
	void save(Equation  equation);

	/**
	 * Description: Deletes the Equation record with the specified ID from the database.
	 *
	 * @param id The ID of the Equation record to be deleted.
	 * @return true if the deletion is successful, false otherwise.
	 */
	boolean delete(Long id);

}
