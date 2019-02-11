package org.arb_tech.web.exception;

/**
 * Custom Exception class to throw our customized application based exceptions
 * @author Aniket.Bharsakale
 */
public class ProjectPortalException extends RuntimeException {

	private static final long serialVersionUID = 772193746001025060L;
	
	public ProjectPortalException(String errorMessage) {
		super(errorMessage);
	}

}
