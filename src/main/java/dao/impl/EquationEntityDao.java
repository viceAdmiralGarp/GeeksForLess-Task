package dao.impl;

import dao.EquationDao;
import equation.Equation;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description:This class represents a Data Access Object (DAO) implementation for interacting with the
 * Equation entity in the database. It provides methods for saving and deleting equations.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class EquationEntityDao implements EquationDao {

	// Singleton instance of the EquationEntityDao class
	private static final EquationEntityDao INSTANCE = new EquationEntityDao();

	// SQL query to delete a record from the 'equations' table based on its ID
	private static final String DELETE_SQL = """
							DELETE FROM equations
							WHERE id = ?
			""";

	// SQL query to insert a new record into the 'equations' table with a specified equation value
	private static final String SAVE_SQL = """
				INSERT INTO equations (equation)
				VALUES (?);
			""";

	// Private constructor to enforce the Singleton pattern
	private EquationEntityDao() {}

	/**
	 * Description:Gets the singleton instance of the EquationEntityDao class.
	 *
	 * @return The singleton instance of EquationEntityDao.
	 */
	public static EquationEntityDao getInstance() {
		return INSTANCE;
	}

	/**
	 * Description:Saves the provided Equation in the 'equations' table.
	 *
	 * @param equation The Equation object to be saved.
	 */
	@Override
	public void save(Equation equation) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(SAVE_SQL)) {
			prepareStatement.setString(1, equation.getEquation());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	/**
	 *Description: Deletes the record with the specified ID from the 'equations' table.
	 *
	 * @param id The ID of the record to be deleted.
	 * @return true if the deletion is successful, false otherwise.
	 */
	@Override
	public boolean delete(Long id) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(DELETE_SQL)) {
			prepareStatement.setLong(1, id);
			return prepareStatement.executeUpdate() > 0; // if return !=0 , failed to delete
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
