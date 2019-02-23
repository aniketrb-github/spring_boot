package org.arb_tech.web.dao;

import java.util.List;

import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.entity.Status;
import org.arb_tech.web.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the Spring Boot's specialty, wherein we just provide our necessary
 * customized DAO method signatures, and the smart spring boot provides its own
 * implementation to our custom method signatures precisely & correctly.
 * 
 * @author Aniket.Bharsakale
 */
@Repository
public interface ITaskRepo extends JpaRepository<Task, Integer> {

	public List<Task> getProjectTasksByProjectId(Project projectId);

	public List<Task> getEmployeeTasksByAssigneeId(Employee assignee);
	
	public List<Task> getEmployeeTasksByReporterId(Employee reporter);
	
	public List<Task> getEmployeeTasksByStatusId(Status status);
}
