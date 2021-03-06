package org.arb_tech.web.util;

/**
 * contains the String Literals, Numeric Constants and other frequently used values 
 * @author Aniket.Bharsakale
 */
public class ApplicationConstants {

	// METHOD ACTIONS
	public enum ACTION {
		GET_ALL_PROJECTS, GET_PROJECT, CREATE_PROJECT,
		GET_ALL_EMPLOYEES, GET_EMPLOYEE, UPDATE_EMPLOYEE, CREATE_EMPLOYEE,
		GET_ALL_TASKS, GET_TASK, CREATE_TASK,
		GET_ALL_BUGS, GET_BUG, CREATE_BUG, UPDATE_BUG;
	}
	
	// CONTROLLER PATHS
	public static final String EMPLOYEES = "/employees"; 
	public static final String BUGS = "/bugs";
	public static final String PROJECTS = "/projects";
	public static final String TASKS = "/tasks";
	
	// URI COMPONENTS
	public static final String PATH_VAR_ID = "/{id}";
	public static final String REQUEST_PARAM_ID = "id";
	
	// IDENTIFIERS
	public static final String STATUS_ID = "statusId";
	public static final String REPORTER_ID = "reporterId";
	public static final String ASSIGNEE_ID = "assigneeId";
	public static final String PROJECT_CODE = "projectCode";
	public static final String TASK_ID = "taskId";
	
	public static final String UTF8_CHARSET_NAME= "UTF-8";
	public static final String MESSAGES = "messages";
}
