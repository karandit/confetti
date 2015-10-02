package org.confetti.xml;

/**
 * Represents an exception that appears during a FAO(File Access Object) operation.
 */
public class FAOException extends Exception {

	/** The default serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new fao exception.
	 */
	public FAOException() {
		// empty.
	}

	/**
	 * Constructs a new fao exception.
	 * 
	 * @param message the exception message.
	 */
	public FAOException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new fao exception.
	 * 
	 * @param cause the cause of this exception.
	 */
	public FAOException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new fao exception.
	 * 
	 * @param message the exception message.
	 * @param cause the cause of this exception.
	 */
	public FAOException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
