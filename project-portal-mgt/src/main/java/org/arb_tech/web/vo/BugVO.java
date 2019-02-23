package org.arb_tech.web.vo;

import java.util.Date;

/**
 * View layer object for Bug entity
 *
 * @author Aniket.Bharsakale
 */
public class BugVO {
	private String name;
	private String description;
	private String projectCode;
	private Integer taskId;
	private Integer statusId;
	private Integer assigneeId;
	private Integer reporterId;
	private Date startDate;
	private Date endDate;

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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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

	public BugVO(String name, String description, String projectCode, Integer taskId, Integer statusId,
			Integer assigneeId, Integer reporterId, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.description = description;
		this.projectCode = projectCode;
		this.taskId = taskId;
		this.statusId = statusId;
		this.assigneeId = assigneeId;
		this.reporterId = reporterId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public BugVO() {
		super();
	}

	@Override
	public String toString() {
		return "BugVO [name=" + name + ", description=" + description + ", projectCode=" + projectCode + ", taskId="
				+ taskId + ", statusId=" + statusId + ", assigneeId=" + assigneeId + ", reporterId=" + reporterId
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
