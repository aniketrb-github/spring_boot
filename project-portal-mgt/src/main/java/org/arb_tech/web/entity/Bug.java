package org.arb_tech.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bug entity which corresponds to table: tbl_bugs in database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_bugs")
public class Bug {
	private Integer id;
	private boolean deleted;
	private String name;
	private String description;
	private Task taskId;
	private BugStatus bugStatusId;
	private Employee employeeId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "deleted", nullable = false)
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

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
