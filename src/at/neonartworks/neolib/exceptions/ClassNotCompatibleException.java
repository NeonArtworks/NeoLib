package at.neonartworks.neolib.exceptions;

/**
 * Indicates, that the Class doesn't match the Filter
 * 
 * @author Alexander Daum
 *
 */
public class ClassNotCompatibleException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassNotCompatibleException(String message) {
		super(message);
	}

	public ClassNotCompatibleException() {
		super();
	}

	public ClassNotCompatibleException(Throwable throwable) {
		super(throwable);
	}

	public ClassNotCompatibleException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
