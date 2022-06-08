package exceptions;

public class DuplicateException extends DAOException {
	private static final long serialVersionUID = -890814663500636201L;

	/** Excepcion con mensaje */
	public DuplicateException(String message) {
		super(message);
	}

	/** Excepcion con mensaje y causa */
	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}
}
