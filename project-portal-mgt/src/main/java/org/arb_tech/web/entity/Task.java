package org.arb_tech.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.arb_tech.web.entity.base.SoftDelete;

@Entity
@Table(name = "tbl_tasks")
public class Task extends SoftDelete {
	private String name;
	private Date startDate;
	private Date endDate;
	private Project projectId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}
}
