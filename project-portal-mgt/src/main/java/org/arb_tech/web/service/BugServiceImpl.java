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
import org.arb_tech.web.vo.BugVO;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public List<Bug> getBugs(Integer statusId, Integer taskId, Integer assigneeId, Integer reporterId,
			String projectCode) {
		Project project = null;
		Task task = null;
		List<Bug> bugsList = null;

		if (null == statusId && null == taskId && null == assigneeId && null == reporterId && null == projectCode) {
			bugsList = bugRepo.findAll();
		} else if (null != statusId) {

			// Fetch all bugs on the basis of this obtained status from user
			Optional<Status> optStatus = statusRepo.findById(statusId);
			if(optStatus.isPresent()) {
				bugsList = bugRepo.getAllBugsByStatusId(optStatus.get());
			} else {
				throw new ProjectPortalException("Invalid Status. No such Status available in database.");
			}
		} else if (null != taskId) {

			// Fetch all bugs related to this task
			 Optional<Task> optTask = taskRepo.findById(taskId);
			if (optTask.isPresent()) {
				bugsList = bugRepo.getAllBugsByTaskId(task);
			} else {
				throw new ProjectPortalException("Invalid Task Id. No such Task is available in database.");
			}

		} else if (null != assigneeId || null != reporterId) {

			// Fetch all bugs assigned to / reported by this employee
			if (null != assigneeId) {
				Optional<Employee> optEmp = empRepo.findById(assigneeId);
				if (optEmp.isPresent()) {
					bugsList = bugRepo.getAllBugsByAssigneeId(optEmp.get());
				} else {
					throw new ProjectPortalException("Invalid Employee Id. No such Employee available in database.");
				}
			} else {
				Optional<Employee> optEmp = empRepo.findById(reporterId);
				if (optEmp.isPresent()) {
					bugsList = bugRepo.getAllBugsByReporterId(optEmp.get());
				} else {
					throw new ProjectPortalException("Invalid Employee Id. No such Employee available in database.");
				}
			}

		} else if (null != projectCode && !projectCode.isEmpty()) {

			// Fetch all bugs related to this project
			project = projectRepo.getProjectByProjectCode(projectCode);
			if (null != project) {
				bugsList = bugRepo.getAllBugsByProjectId(project);
			} else {
				throw new ProjectPortalException("Invalid Project Id. No such Project available in database.");
			}
		}
		return (null != bugsList && !bugsList.isEmpty()) ? bugsList : null;
	}

	@Override
	public Bug createBug(BugVO bugVO) {
		if (null != bugVO) {
			return updateOrSaveBugEntity(bugVO, new Bug());
		} else {
			throw new ProjectPortalException("Bug Entity to create cannot be null!");
		}
	}

	@Override
	public Bug updateBug(Integer bugId, BugVO bugVO) {
		Bug bugEntity = null;
		if (null != bugId) {
			bugEntity = bugRepo.findById(bugId).get();
			if (null != bugEntity) {
				return updateOrSaveBugEntity(bugVO, bugEntity);
			} else {
				throw new ProjectPortalException("No such Bug for given bug id" + bugId + " found in database.");
			}
		} else {
			throw new ProjectPortalException("Bug Id cannot be null!");
		}
	}

	@Override
	public String deleteBug(Integer bugId) {
		Bug bugEntity = null;
		if (null != bugId) {
			bugEntity = bugRepo.findById(bugId).get();
			if (null != bugEntity) {
				bugRepo.delete(bugEntity);
				return "Bug with given bugId: " + bugId + " deleted successfully from database.";
			} else {
				throw new ProjectPortalException("No such Bug for given bug id" + bugId + " found in database.");
			}
		} else {
			throw new ProjectPortalException("Bug Id cannot be null!");
		}
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
				throw new ProjectPortalException("Invalid Status. No such Status available in database.");
			}
		}

		if (null != bugVO.getTaskId()) {
			task = taskRepo.findById(bugVO.getTaskId()).get();
			if (null != task) {
				bugEntity.setTaskId(task);
			} else {
				throw new ProjectPortalException("Invalid Task Id. No such Task is available in database.");
			}
		}

		if (null != bugVO.getAssigneeId()) {
			employee = empRepo.findById(bugVO.getAssigneeId()).get();
			if (null != employee) {
				bugEntity.setAssigneeId(employee);
			} else {
				throw new ProjectPortalException("Invalid Employee Id. No such Employee available in database.");
			}
		}

		if (null != bugVO.getReporterId()) {
			employee = empRepo.findById(bugVO.getReporterId()).get();
			if (null != employee) {
				bugEntity.setReporterId(employee);
			} else {
				throw new ProjectPortalException("Invalid Employee Id. No such Employee available in database.");
			}
		}

		if (null != bugVO.getProjectCode() && !bugVO.getProjectCode().isEmpty()) {
			project = projectRepo.getProjectByProjectCode(bugVO.getProjectCode());
			if (null != project) {
				bugEntity.setProjectId(project);
			} else {
				throw new ProjectPortalException("Invalid Project Id. No such Project available in database.");
			}
		}
		
		if(null != bugVO.getDescription())
			bugEntity.setDescription(bugVO.getDescription());
		
		if(null != bugVO.getName())
			bugEntity.setName(bugVO.getName());
		
		if(null != bugVO.getEndDate())
			bugEntity.setEndDate(bugVO.getEndDate());
		
		if(null != bugVO.getStartDate())
			bugEntity.setStartDate(bugVO.getStartDate());
		
		return bugRepo.save(bugEntity);
	}
}
