package org.arb_tech.web.dao;

import java.util.List;

import org.arb_tech.web.entity.Bug;
import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.entity.Status;
import org.arb_tech.web.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Bug Entity
 * @author Aniket.Bharsakale
 */
@Repository
public interface IBugRepo extends JpaRepository<Bug, Integer> {
	List<Bug> getAllBugsByStatusId(Status statusId);
	
	List<Bug> getAllBugsByProjectId(Project projectId);
	
	List<Bug> getAllBugsByTaskId(Task taskId);
	
	List<Bug> getAllBugsByAssigneeId(Employee assigneeId); 
	
	List<Bug> getAllBugsByReporterId(Employee reporterId);
}
