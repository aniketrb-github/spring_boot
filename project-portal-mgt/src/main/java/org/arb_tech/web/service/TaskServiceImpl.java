package org.arb_tech.web.service;

import java.util.ArrayList;
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
import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private MessageResolver msgResolver;

	@Override
	public ResponseEntity<?> getTasks(String projectCode, Integer assigneeId, Integer reporterId, Integer statusId)
			throws ProjectException {
		ResponseEntity<?> response = null;
		Project projectId = null;
		Status status = null;
		List<Task> taskList = null;
		Optional<Employee> optEmployee = null;
		List<TaskVO> taskVOList = null;

		if (null == projectCode && null == assigneeId && null == reporterId && null == statusId) {

			// Get all tasks from the database - no filter
			taskList = taskRepo.findAll();
			if (null != taskList) {
				taskVOList = populateTaskVOList(taskList);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskVOList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.OK)
						.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.NO_TASKS_IN_DB,
								msgResolver.resolveLocalizedMessage(Messages.NO_TASKS_IN_DB), taskList, null));
			}

		} else if (null != projectCode && !projectCode.isEmpty()) {

			// Get all tasks related to this project only
			projectId = projectRepo.getProjectByProjectCode(projectCode);
			if (null != projectId) {
				taskList = taskRepo.getProjectTasksByProjectId(projectId);
				taskVOList = populateTaskVOList(taskList);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskVOList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.PROJECT_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
			}
		} else if (null != assigneeId) {

			// Get all tasks assigned to this employee only
			optEmployee = employeeRepo.findById(assigneeId);
			if (optEmployee.isPresent()) {
				taskList = taskRepo.getEmployeeTasksByAssigneeId(optEmployee.get());
				taskVOList = populateTaskVOList(taskList);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskVOList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.EMP_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), null, null));
			}
		} else if (null != reporterId) {

			// Get all tasks reported by this employee only
			optEmployee = employeeRepo.findById(reporterId);
			if (optEmployee.isPresent()) {
				taskList = taskRepo.getEmployeeTasksByReporterId(optEmployee.get());
				taskVOList = populateTaskVOList(taskList);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskVOList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.EMP_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), null, null));
			}
		} else if (null != statusId) {

			// Get all tasks which have this task status name
			status = statusRepo.findById(statusId).get();
			if (null != status) {
				taskList = taskRepo.getEmployeeTasksByStatusId(status);
				taskVOList = populateTaskVOList(taskList);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskVOList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.STATUS_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.STATUS_NOT_FOUND), null, null));
			}
		}

		return response;
	}

	@Override
	public ResponseEntity<?> createTask(TaskVO taskVO) throws ProjectException {
		ResponseEntity<?> response = null;

		if (null != taskVO) {
			TaskVO taskViewObject = null;
			Task updatedTaskEntity = this.updateOrSaveTaskEntity(taskVO, new Task());
			if (null != updatedTaskEntity) {
				taskViewObject = populateTaskVO(updatedTaskEntity);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskViewObject, null));
			}
		} else {
			throw new ProjectException(msgResolver.resolveLocalizedMessage(Messages.TASK_VO_NULL));
		}

		return response;
	}

	@Override
	public ResponseEntity<?> updateTask(Integer taskId, TaskVO taskVO) throws ProjectException {
		ResponseEntity<?> response = null;
		TaskVO taskViewObject = null;
		
		if (null != taskId && null != taskVO) {
			Task taskEntity = taskRepo.findById(taskId).get();
			if (null != taskEntity) {
				Task updatedTaskEntity = this.updateOrSaveTaskEntity(taskVO, taskEntity);
				if (null != updatedTaskEntity) {
					taskViewObject = populateTaskVO(updatedTaskEntity);
					response = ResponseEntity.status(HttpStatus.OK)
							.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MSG_OK,
									msgResolver.resolveLocalizedMessage(Messages.MSG_OK), taskViewObject, null));
				}

			} else {
				response = ResponseEntity.status(HttpStatus.OK)
						.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.TASK_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.TASK_NOT_FOUND), taskEntity, null));
			}
		} else {
			throw new ProjectException(msgResolver.resolveLocalizedMessage(Messages.TASK_VO_NULL));
		}

		return response;
	}

	@Override
	public ResponseEntity<?> deleteTask(Integer taskId) {
		ResponseEntity<?> response = null;
		Task taskEntity = null;

		if (null != taskId) {
			taskEntity = taskRepo.findById(taskId).get();
			if (null != taskEntity) {
				taskRepo.delete(taskEntity);
				response = ResponseEntity.status(HttpStatus.OK)
						.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.TASK_DELETE_SUCCESS,
								msgResolver.resolveLocalizedMessage(Messages.TASK_DELETE_SUCCESS), null, null));
			} else {
				response = ResponseEntity.status(HttpStatus.OK)
						.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.TASK_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.TASK_NOT_FOUND), taskEntity, null));
			}
		} else {
			throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.TASK_ID_NULL));
		}
		return response;
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

		if (null != taskVO.getName())
			taskEntity.setName(taskVO.getName());

		if (null != taskVO.getDescription())
			taskEntity.setDescription(taskVO.getDescription());

		return taskRepo.save(taskEntity);
	}
	
	private List<TaskVO> populateTaskVOList(List<Task> taskList) {
		List<TaskVO> taskVOList = new ArrayList<>();
		TaskVO taskVO = null;
		for(Task task : taskList) {
			taskVO = new TaskVO();
			taskVO.setName(task.getName());
			taskVO.setDescription(task.getDescription());
			taskVO.setAssigneeId(task.getAssigneeId().getId());
			taskVO.setReporterId(task.getReporterId().getId());
			taskVO.setEndDate(task.getEndDate());
			taskVO.setStartDate(task.getStartDate());
			taskVO.setProjectCode(task.getProjectId().getProjectCode());
			taskVO.setStatusId(task.getStatusId().getId());
			taskVOList.add(taskVO);
		}
		return taskVOList;
	}
	
	private TaskVO populateTaskVO(Task task) {
		TaskVO taskVO = new TaskVO();
		taskVO.setName(task.getName());
		taskVO.setDescription(task.getDescription());
		taskVO.setAssigneeId(task.getAssigneeId().getId());
		taskVO.setReporterId(task.getReporterId().getId());
		taskVO.setEndDate(task.getEndDate());
		taskVO.setStartDate(task.getStartDate());
		taskVO.setProjectCode(task.getProjectId().getProjectCode());
		taskVO.setStatusId(task.getStatusId().getId());
		return taskVO;
	}
}
