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
 * Task entity which corresponds to table: tbl_tasks in database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_tasks")
public class Task {
	private Integer id;
	private String name;
	private boolean deleted;
	private String description;
	private Project projectId;
	private Date startDate;
	private Date endDate;
	private Employee assigneeId;
	private Employee reporterId;
	private Status taskStatus;

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

	@Column(name = "start_date", nullable = true)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", nullable = true)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "task_status", nullable = false)
	public Status getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}

	@ManyToOne
	@JoinColumn(name = "assigned_to", nullable = false)
	public Employee getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Employee assigneeId) {
		this.assigneeId = assigneeId;
	}

	@ManyToOne
	@JoinColumn(name = "reported_by", nullable = false)
	public Employee getReporterId() {
		return reporterId;
	}

	public void setReporterId(Employee reporterId) {
		this.reporterId = reporterId;
	}
}