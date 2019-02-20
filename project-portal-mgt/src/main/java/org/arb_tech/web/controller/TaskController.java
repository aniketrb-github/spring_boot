package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Task;
import org.arb_tech.web.service.ITaskService;
import org.arb_tech.web.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for Task
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
	/*
	 3.	Tasks
	a.	New Task			POST: createTask
	b.	Tasks List			GET: getAllTasks
	c.	Update Task			PUT: updateTaskById
	d.	Delete Task			DELETE: deleteTaskById
	 */
	@Autowired
	private ITaskService taskService;
	
	@GetMapping
	public @ResponseBody List<Task> getTasks(@RequestParam(name = "projectCode", required = false) String projectCode,
			@RequestParam(name = "assigneeId", required = false) Integer assigneeId, 
			@RequestParam(name = "reporterId", required = false) Integer reporterId) {
		return taskService.getTasks(projectCode, assigneeId, reporterId);
	}
	
	@PostMapping
	public @ResponseBody Task createTask(@RequestBody TaskVO taskVO) {
		return taskService.createTask(taskVO);
	}

}
