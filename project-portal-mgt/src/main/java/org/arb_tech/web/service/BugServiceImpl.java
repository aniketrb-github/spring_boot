package org.arb_tech.web.service;

import java.util.List;
import java.util.Optional;

import org.arb_tech.web.dao.IBugRepo;
import org.arb_tech.web.dao.IEmployeeRepo;
import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.dao.IStatusRepo;
import org.arb_tech.web.dao.ITaskRepo;
import org.arb_tech.web.entity.Bug;
import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.entity.Status;
import org.arb_tech.web.entity.Task;
import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.BugVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for Bug Entity class
 * 
 * @author Aniket.Bharsakale
 */
@Service
public class BugServiceImpl implements IBugService {

	@Autowired
	private IBugRepo bugRepo;

	@Autowired
	private IProjectRepo projectRepo;

	@Autowired
	private ITaskRepo taskRepo;

	@Autowired
	private IStatusRepo statusRepo;

	@Autowired
	private IEmployeeRepo empRepo;

	@Autowired
	private MessageResolver msgResolver;

	@Override
	public ResponseEntity<?> getBugs(Integer statusId, Integer taskId, Integer assigneeId, Integer reporterId,
			String projectCode) throws ProjectException {
		ResponseEntity<?> response = null;
		Project project = null;
		Task task = null;
		List<Bug> bugsList = null;

		if (null == statusId && null == taskId && null == assigneeId && null == reporterId && null == projectCode) {
			bugsList = bugRepo.findAll();
			if (null != bugsList) {
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugsList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.BUG_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.BUG_NOT_FOUND)));
			}

		} else if (null != statusId) {

			// Fetch all bugs on the basis of this obtained status from user
			Optional<Status> optStatus = statusRepo.findById(statusId);
			if (optStatus.isPresent()) {
				bugsList = bugRepo.getAllBugsByStatusId(optStatus.get());
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugsList));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.STATUS_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.STATUS_NOT_FOUND)));
			}

		} else if (null != taskId) {

			// Fetch all bugs related to this task
			Optional<Task> optTask = taskRepo.findById(taskId);
			if (optTask.isPresent()) {
				bugsList = bugRepo.getAllBugsByTaskId(task);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugsList));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.TASK_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.TASK_NOT_FOUND)));
			}

		} else if (null != assigneeId || null != reporterId) {

			// Fetch all bugs assigned to / reported by this employee
			if (null != assigneeId) {
				Optional<Employee> optEmp = empRepo.findById(assigneeId);
				if (optEmp.isPresent()) {
					bugsList = bugRepo.getAllBugsByAssigneeId(optEmp.get());
					response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
							Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugsList));
				} else {
					response = ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.EMP_NOT_FOUND,
									msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), null));
				}
			} else {
				Optional<Employee> optEmp = empRepo.findById(reporterId);
				if (optEmp.isPresent()) {
					bugsList = bugRepo.getAllBugsByReporterId(optEmp.get());
					response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
							Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugsList));
				} else {
					response = ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.EMP_NOT_FOUND,
									msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), null));
				}
			}

		} else if (null != projectCode && !projectCode.isEmpty()) {

			// Fetch all bugs related to this project
			project = projectRepo.getProjectByProjectCode(projectCode);
			if (null != project) {
				bugsList = bugRepo.getAllBugsByProjectId(project);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugsList));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.PROJECT_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
			}
		}
		return response;
	}

	@Override
	public ResponseEntity<?> createBug(BugVO bugVO) throws ProjectException {
		ResponseEntity<?> response = null;
		Bug bugEntity = null;

		if (null != bugVO) {
			bugEntity = updateOrSaveBugEntity(bugVO, new Bug());
			response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
					Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), bugEntity));
		} else {
			throw new ProjectException(msgResolver.resolveLocalizedMessage(Messages.BUG_VO_NULL));
		}
		return response;
	}

	@Override
	public ResponseEntity<?> updateBug(Integer bugId, BugVO bugVO) throws ProjectException {
		ResponseEntity<?> response = null;
		Bug bugEntity = null;
		Bug updatedBugEntity = null;

		if (null != bugId) {
			bugEntity = bugRepo.findById(bugId).get();
			if (null != bugEntity) {
				updatedBugEntity = updateOrSaveBugEntity(bugVO, bugEntity);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), updatedBugEntity));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.BUG_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.BUG_NOT_FOUND)));
			}
		} else {
			throw new ProjectPortalException(Messages.BUG_ID_NULL);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> deleteBug(Integer bugId) throws ProjectException {
		ResponseEntity<?> response = null;
		Bug bugEntity = null;

		if (null != bugId) {
			bugEntity = bugRepo.findById(bugId).get();
			if (null != bugEntity) {
				// Soft Deleting the Entity
				bugEntity.setDeleted(Boolean.TRUE);
				bugRepo.save(bugEntity);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK)));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.BUG_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.BUG_NOT_FOUND)));
			}
		} else {
			throw new ProjectPortalException(Messages.BUG_ID_NULL);
		}
		return response;
	}

	public Bug updateOrSaveBugEntity(BugVO bugVO, Bug bugEntity) {
		Project project = null;
		Employee employee = null;
		Task task = null;
		Status status = null;

		if (null != bugVO.getStatusId()) {
			status = statusRepo.findById(bugVO.getStatusId()).get();
			if (null != status) {
				bugEntity.setStatusId(status);
			} else {
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.STATUS_NOT_FOUND));
			}
		}

		if (null != bugVO.getTaskId()) {
			task = taskRepo.findById(bugVO.getTaskId()).get();
			if (null != task) {
				bugEntity.setTaskId(task);
			} else {
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.TASK_NOT_FOUND));
			}
		}

		if (null != bugVO.getAssigneeId()) {
			employee = empRepo.findById(bugVO.getAssigneeId()).get();
			if (null != employee) {
				bugEntity.setAssigneeId(employee);
			} else {
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage("Assignee "+Messages.EMP_NOT_FOUND));
			}
		}

		if (null != bugVO.getReporterId()) {
			employee = empRepo.findById(bugVO.getReporterId()).get();
			if (null != employee) {
				bugEntity.setReporterId(employee);
			} else {
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage("Reporter "+Messages.EMP_NOT_FOUND));
			}
		}

		if (null != bugVO.getProjectCode() && !bugVO.getProjectCode().isEmpty()) {
			project = projectRepo.getProjectByProjectCode(bugVO.getProjectCode());
			if (null != project) {
				bugEntity.setProjectId(project);
			} else {
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND));
			}
		}

		if (null != bugVO.getDescription())
			bugEntity.setDescription(bugVO.getDescription());

		if (null != bugVO.getName())
			bugEntity.setName(bugVO.getName());

		if (null != bugVO.getEndDate())
			bugEntity.setEndDate(bugVO.getEndDate());

		if (null != bugVO.getStartDate())
			bugEntity.setStartDate(bugVO.getStartDate());

		return bugRepo.save(bugEntity);
	}
}
