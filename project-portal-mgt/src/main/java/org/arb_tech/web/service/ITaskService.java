package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.entity.Task;
import org.arb_tech.web.vo.TaskVO;

/**
 * task service interface, which contains the skeleton methods of task entity
 * @author Aniket.Bharsakale
 */
public interface ITaskService {
	public List<Task> getTasks(String projectCode, Integer assigneeId, Integer reporterId);
	
	public Task createTask(TaskVO taskVO);
	
	public Task updateTask(Integer taskId, TaskVO taskVO);
	
	public String deleteTask(Integer taskId);
}
