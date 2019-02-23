package org.arb_tech.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Bug entity which corresponds to table: tbl_bugs in database
 * 
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_bugs")
public class Bug {
	private Integer id;
	private boolean deleted;
	private String name;
	private String description;
	private Project projectId;
	private Task taskId;
	private Status statusId;
	private Employee assigneeId;
	private Employee reporterId;
	private Date startDate;
	private Date endDate;

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

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	public Status getStatusId() {
		return statusId;
	}

	public void setStatusId(Status statusId) {
		this.statusId = statusId;
	}

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	@ManyToOne
	@JoinColumn(name = "assignee_id", nullable = false)
	public Employee getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Employee assigneeId) {
		this.assigneeId = assigneeId;
	}

	@ManyToOne
	@JoinColumn(name = "reporter_id", nullable = false)
	public Employee getReporterId() {
		return reporterId;
	}

	public void setReporterId(Employee reporterId) {
		this.reporterId = reporterId;
	}

	@Column(name = "start_date", nullable = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", nullable = false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
