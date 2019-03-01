package org.arb_tech.web.exception;

/**
 * Custom Exception class to throw our customized application based exceptions
 * 
 * @author Aniket.Bharsakale
 */
public class ProjectException extends Exception {

	private static final long serialVersionUID = -3551510160904441761L;

	private String action;
	private String exceptionCode;
	private String message;

	public ProjectException() {
	}

	public ProjectException(String message) {
		super(message);
	}

	public ProjectException(String action, String exceptionCode, String message) {

		this.action = action;
		this.exceptionCode = exceptionCode;
		this.message = message;
	}

	public ProjectException(Throwable cause) {
		super(cause);
	}

	public ProjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}