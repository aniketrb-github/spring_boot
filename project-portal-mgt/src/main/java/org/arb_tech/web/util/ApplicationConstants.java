package org.arb_tech.web.util;

/**
 * contains the String Literals, Numeric Constants and other frequently used values 
 * @author Aniket.Bharsakale
 */
public class ApplicationConstants {

	public enum ACTION {
		GET_ALL_PROJECTS, GET_PROJECT, CREATE_PROJECT,
		GET_ALL_EMPLOYEES, GET_EMPLOYEE, UPDATE_EMPLOYEE, CREATE_EMPLOYEE,
		GET_ALL_TASKS, GET_TASK, CREATE_TASK,
		GET_ALL_BUGS, GET_BUG, CREATE_BUG, UPDATE_BUG;
	}
	
	// CONTROLLER PATH
	public static final String EMPLOYEES = "/employee"; 
	public static final String BUGS = "/bugs";
	public static final String PROJECTS = "/projects";
	public static final String TASKS = "/tasks";
	public static final String PATH_VAR_ID = "/{id}";
	
	public static final String UTF8_CHARSET_NAME= "UTF-8";
	public static final String MESSAGES = "messages";
}
