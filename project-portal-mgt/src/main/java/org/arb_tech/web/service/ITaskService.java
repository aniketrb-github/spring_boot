package org.arb_tech.web.service;

import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.vo.TaskVO;
import org.springframework.http.ResponseEntity;

/**
 * task service interface, which contains the skeleton methods of task entity
 * @author Aniket.Bharsakale
 */
public interface ITaskService {
	public ResponseEntity<?> getTasks(String projectCode, Integer assigneeId, Integer reporterId, Integer statusId) throws ProjectException;
	
	public ResponseEntity<?> createTask(TaskVO taskVO) throws ProjectException;
	
	public ResponseEntity<?> updateTask(Integer taskId, TaskVO taskVO) throws ProjectException;
	
	public ResponseEntity<?> deleteTask(Integer taskId) throws ProjectException;
}
