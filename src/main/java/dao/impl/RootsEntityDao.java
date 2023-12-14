package dao.impl;

import dao.RootsDao;
import equation.Equation;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Description:This class represents a Data Access Object (DAO) implementation for interacting with the Roots entity in the database.
 * It provides methods for saving, deleting, and finding equations associated with roots.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class RootsEntityDao implements RootsDao {


	// Singleton instance of the RootsEntityDao class
	private static final RootsEntityDao INSTANCE = new RootsEntityDao();

	// SQL query to delete a record from the 'roots' table based on its ID
	private static final String DELETE_SQL = """
							DELETE FROM roots
							WHERE id = ?
			""";

	// SQL query to insert a new record into the 'roots' table with a specified root value
	private static final String SAVE_SQL = """
				INSERT INTO roots (root)
				VALUES (?);
			""";

	// SQL query to find equations associated with a specified root in the 'Roots' and 'Equations' tables
	private static final String FIND_EQUATIONS_BY_ID_SQL = """
				SELECT e.equation
			    FROM Roots r
			    JOIN Equations e ON r.equation_id = e.id
			    WHERE r.root = ?;
			""";

	// Private constructor to enforce the Singleton pattern
	private RootsEntityDao() {
	}

	/**
	 * Description:Gets the singleton instance of the RootsEntityDao class.
	 *
	 * @return The singleton instance of RootsEntityDao.
	 */
	public static RootsEntityDao getInstance() {
		return INSTANCE;
	}

	/**
	 * Description:Saves the provided Equation in the 'roots' table.
	 *
	 * @param equation The Equation object to be saved.
	 */
	@Override
	public void save(Equation equation) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(SAVE_SQL)) {
			prepareStatement.setDouble(1, equation.getRoot());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Description:Deletes the record with the specified ID from the 'roots' table.
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


	/**
	 * Description:Finds and prints all equations associated with the specified root.
	 *
	 * @param equation The Equation object containing the root value for which associated equations should
	 * be retrieved and printed.
	 */
	@Override
	public void findEquationsByRoot(Equation equation) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(FIND_EQUATIONS_BY_ID_SQL)) {
			prepareStatement.setLong(1, (long) equation.getRoot());
			var resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String equationText = resultSet.getString("equation");
				System.out.println(equationText);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
