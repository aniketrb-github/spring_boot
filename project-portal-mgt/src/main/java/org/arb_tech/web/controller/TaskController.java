package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Task;
import org.arb_tech.web.service.ITaskService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.TaskVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@Autowired
	private MessageResolver msgResolver;
	
	private static Logger log = LoggerFactory.getLogger(ProjectController.class);

	@GetMapping
	public @ResponseBody ResponseEntity<?> getTasks(@RequestParam(name = "projectCode", required = false) String projectCode,
			@RequestParam(name = "assigneeId", required = false) Integer assigneeId,
			@RequestParam(name = "reporterId", required = false) Integer reporterId,
			@RequestParam(name = "statusId", required = false) Integer statusId) {
		log.info("<<< executing [ TaskController -> getTasks() ] >>>");
		
		List<Task> tasksList = taskService.getTasks(projectCode, assigneeId, reporterId, statusId);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), tasksList, null));
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> createTask(@RequestBody TaskVO taskVO) {
		log.info("<<< executing [ TaskController -> createTask() ] >>>");
		
		Task task  = taskService.createTask(taskVO);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), task, null));
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody TaskVO taskVO) {
		log.info("<<< executing [ TaskController -> updateTask() ] >>>");
		
		Task task  = taskService.updateTask(id, taskVO);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), task, null));
	}

	@DeleteMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> deleteTask(@PathVariable Integer id) {
		log.info("<<< executing [ TaskController -> deleteTask() ] >>>");
		
		String response = taskService.deleteTask(id);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), response, null));
	}

}
