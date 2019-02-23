package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Task;
import org.arb_tech.web.service.ITaskService;
import org.arb_tech.web.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/tasks")
public class TaskController {
	@Autowired
	private ITaskService taskService;

	@GetMapping
	public @ResponseBody List<Task> getTasks(@RequestParam(name = "projectCode", required = false) String projectCode,
			@RequestParam(name = "assigneeId", required = false) Integer assigneeId,
			@RequestParam(name = "reporterId", required = false) Integer reporterId,
			@RequestParam(name = "statusId", required = false) Integer statusId) {
		return taskService.getTasks(projectCode, assigneeId, reporterId, statusId);
	}

	@PostMapping
	public @ResponseBody Task createTask(@RequestBody TaskVO taskVO) {
		return taskService.createTask(taskVO);
	}

	@PutMapping(path = "/{taskId}")
	public @ResponseBody Task updateTask(@PathVariable Integer taskId, @RequestBody TaskVO taskVO) {
		return taskService.updateTask(taskId, taskVO);
	}

	@DeleteMapping(path = "/{taskId}")
	public @ResponseBody String deleteTask(@PathVariable Integer taskId) {
		return taskService.deleteTask(taskId);
	}

}
