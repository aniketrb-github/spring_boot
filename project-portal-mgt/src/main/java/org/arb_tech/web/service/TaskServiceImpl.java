package org.arb_tech.web.service;

import java.util.List;
import java.util.Optional;

import org.arb_tech.web.dao.IEmployeeRepo;
import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.dao.IStatusRepo;
import org.arb_tech.web.dao.ITaskRepo;
import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.entity.Status;
import org.arb_tech.web.entity.Task;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Layer, where in we write the business logic
 * 
 * @author Aniket.Bharsakale
 */
@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private ITaskRepo taskRepo;

	@Autowired
	private IProjectRepo projectRepo;

	@Autowired
	private IEmployeeRepo employeeRepo;

	@Autowired
	private IStatusRepo statusRepo;

	@Override
	public List<Task> getTasks(String projectCode, Integer assigneeId, Integer reporterId, Integer statusId) {
		Project projectId = null;
		Status status = null;
		List<Task> taskList = null;
		Optional<Employee> optEmployee = null;

		if (null == projectCode && null == assigneeId && null == reporterId) {

			// Get all tasks from the database - no filter
			taskList = taskRepo.findAll();

		} else if (null != projectCode && !projectCode.isEmpty()) {

			// Get all tasks related to this project only
			projectId = projectRepo.getProjectByProjectCode(projectCode);
			if (null != projectId)
				taskList = taskRepo.getProjectTasksByProjectId(projectId);
			else
				throw new ProjectPortalException("Project with projectCode:" + projectCode + " does not exist.");

		} else if (null != assigneeId) {

			// Get all tasks assigned to this employee only
			optEmployee = employeeRepo.findById(assigneeId);
			if (optEmployee.isPresent())
				taskList = taskRepo.getEmployeeTasksByAssigneeId(optEmployee.get().getId());
			else
				throw new ProjectPortalException("Employee with emplooyeeID:" + assigneeId + " not found in database");

		} else if (null != reporterId) {

			// Get all tasks reported by this employee only
			optEmployee = employeeRepo.findById(reporterId);
			if (optEmployee.isPresent())
				taskList = taskRepo.getEmployeeTasksByReporterId(optEmployee.get().getId());
			else
				throw new ProjectPortalException("Employee with emplooyeeID:" + reporterId + " not found in database");
		} else if(null != statusId) {
			status = statusRepo.findById(statusId).get();
			if(null != status) {
				taskList = taskRepo.getEmployeeTasksByStatusId(status.getId());
			} else {
				throw new ProjectPortalException("Such Project Status is not available in the application."
						+ "\nPlease select some other suitable proejct status.");
			}
		}

		return (null != taskList && !taskList.isEmpty()) ? taskList : null;
	}

	@Override
	public Task createTask(TaskVO taskVO) {
		Task taskEntity = null;
		if (null != taskVO) {
			taskEntity = new Task();
			return this.updateOrSaveTaskEntity(taskVO, taskEntity);
		} else {
			throw new ProjectPortalException(
					"Task Object received:" + taskVO + ".\nTask to be created cannot be null.");
		}
	}

	@Override
	public Task updateTask(Integer taskId, TaskVO taskVO) {
		Task taskEntity = null;
		if (null != taskId) {
			taskEntity = taskRepo.findById(taskId).get();
			if (null != taskEntity) {
				taskEntity = this.updateOrSaveTaskEntity(taskVO, taskEntity);
			}
		}
		return taskEntity;
	}

	@Override
	public String deleteTask(Integer taskId) {
		Task taskEntity = null;
		if (null != taskId) {
			taskEntity = taskRepo.findById(taskId).get();
			if (null != taskEntity) {
				taskRepo.delete(taskEntity);
				return "Task deleted successfully with task id: " + taskId;
			} else {
				return "No such task exist in database for the given task id:" + taskId;
			}
		} else
			throw new ProjectPortalException("Task Id cannot be null.");
	}

	public Task updateOrSaveTaskEntity(TaskVO taskVO, Task taskEntity) {
		Project project = null;
		Optional<Employee> optAssignee = null;
		Optional<Employee> optReporter = null;
		Status taskStatus = null;

		if (null != taskVO.getProjectCode() && !taskVO.getProjectCode().isEmpty()) {
			project = projectRepo.getProjectByProjectCode(taskVO.getProjectCode());
			if (null != project)
				taskEntity.setProjectId(project);
		}

		if (null != taskVO.getStatusId()) {
			taskStatus = statusRepo.findById(taskVO.getStatusId()).get();
			if (null != taskStatus)
				taskEntity.setStatusId(taskStatus);
		}

		if (null != taskVO.getAssigneeId()) {
			optAssignee = employeeRepo.findById(taskVO.getAssigneeId());
			if (optAssignee.isPresent())
				taskEntity.setAssigneeId(optAssignee.get());
		}

		if (null != taskVO.getReporterId()) {
			optReporter = employeeRepo.findById(taskVO.getReporterId());
			if (optReporter.isPresent())
				taskEntity.setReporterId(optReporter.get());
		}

		if (null != taskVO.getStartDate())
			taskEntity.setStartDate(taskVO.getStartDate());

		if (null != taskVO.getEndDate())
			taskEntity.setEndDate(taskVO.getEndDate());

		taskEntity.setName(taskVO.getName());

		if (null != taskVO.getDescription())
			taskEntity.setDescription(taskVO.getDescription());

		return taskRepo.save(taskEntity);
	}
}
