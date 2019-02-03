package io.javabrains.exception;

/**
 * Custom Exception class written to provide error messages to user
 * @author Aniket.Bharsakale
 */
public class TopicException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TopicException(String message) {
		super(message);
	}
}
