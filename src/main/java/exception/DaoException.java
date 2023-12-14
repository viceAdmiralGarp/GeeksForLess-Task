package exception;

/**
 * Description:Custom runtime exception class for handling exceptions in DAO (Data Access Object) operations.
 * Extends RuntimeException to indicate that instances of this exception do not need to be declared
 * in method signatures or caught explicitly.
 *
 * Since: 1.0
 * Author: Matvey Marchenko.
 */
public class DaoException extends RuntimeException{

	/**
	 * Description:Constructs a DaoException with the specified cause
	 * (the Throwable instance that caused this exception).
	 *
	 * @param throwable The cause of the exception.
	 */
	public DaoException(Throwable throwable){
		super(throwable);
	}
}
