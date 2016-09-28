package at.neonartworks.neolib.exceptions;

/**
 * An Exception if an Overflow occured, where it is critical if this happens
 * 
 * @author Alexander Daum
 *
 */
public class OverflowException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5904470434638031035L;

	public OverflowException(String message) {
		super(message);
	}

	public OverflowException() {
		super();
	}

	public OverflowException(Throwable throwable) {
		super(throwable);
	}

	public OverflowException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
