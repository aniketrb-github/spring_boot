package org.arb_tech.web.vo;

import java.util.Date;

/**
 * View layer object for Task entity
 * @author Aniket.Bharsakale
 */
public class TaskVO {
	private String name;
	private String description;
	private String projectCode;
	private Date startDate;
	private Date endDate;
	private Integer assigneeId;
	private Integer reporterId;
	private String taskStatus;

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

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Integer assigneeId) {
		this.assigneeId = assigneeId;
	}

	public Integer getReporterId() {
		return reporterId;
	}

	public void setReporterId(Integer reporterId) {
		this.reporterId = reporterId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "TaskVO [name=" + name + ", description=" + description + ", projectCode=" + projectCode + ", startDate="
				+ startDate + ", endDate=" + endDate + ", assigneeId=" + assigneeId + ", reporterId=" + reporterId
				+ ", taskStatus=" + taskStatus + "]";
	}

	public TaskVO(String name, String description, String projectCode, Date startDate, Date endDate, Integer assigneeId,
			Integer reporterId, String taskStatus) {
		super();
		this.name = name;
		this.description = description;
		this.projectCode = projectCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assigneeId = assigneeId;
		this.reporterId = reporterId;
		this.taskStatus = taskStatus;
	}

	public TaskVO() {
		super();
	}

}
