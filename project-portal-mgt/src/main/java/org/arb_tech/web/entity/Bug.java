package org.arb_tech.web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.arb_tech.web.entity.base.SoftDelete;

@Entity
@Table(name = "tbl_bugs")
public class Bug extends SoftDelete {
	private String name;
	private String description;
	private Task taskId;
	private BugStatus bugStatusId;
	private Employee employeeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}

	public BugStatus getBugStatusId() {
		return bugStatusId;
	}

	public void setBugStatusId(BugStatus bugStatusId) {
		this.bugStatusId = bugStatusId;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}
}
