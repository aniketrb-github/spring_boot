package org.arb_tech.web.controller;

import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.service.ITaskService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.vo.TaskVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for Task
 * 
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = ApplicationConstants.TASKS)
public class TaskController {
	@Autowired
	private ITaskService taskService;
	
	private static Logger log = LoggerFactory.getLogger(TaskController.class);

	@GetMapping
	public @ResponseBody ResponseEntity<?> getTasks(@RequestParam(name = "projectCode", required = false) String projectCode,
			@RequestParam(name = "assigneeId", required = false) Integer assigneeId,
			@RequestParam(name = "reporterId", required = false) Integer reporterId,
			@RequestParam(name = "statusId", required = false) Integer statusId) throws ProjectException {

		log.info("<<< executing [ TaskController -> getTasks() ] >>>");
		return taskService.getTasks(projectCode, assigneeId, reporterId, statusId);
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> createTask(@RequestBody TaskVO taskVO) throws ProjectException {

		log.info("<<< executing [ TaskController -> createTask() ] >>>");
		return taskService.createTask(taskVO);
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody TaskVO taskVO) throws ProjectException {

		log.info("<<< executing [ TaskController -> updateTask() ] >>>");
		return taskService.updateTask(id, taskVO);
	}

	@DeleteMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> deleteTask(@PathVariable Integer id) throws ProjectException {

		log.info("<<< executing [ TaskController -> deleteTask() ] >>>");
		return taskService.deleteTask(id);
	}

}
