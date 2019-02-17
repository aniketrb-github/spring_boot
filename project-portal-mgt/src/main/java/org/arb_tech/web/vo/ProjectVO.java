package org.arb_tech.web.vo;

import java.util.Date;

public class ProjectVO {
	private String name;
	private Date startDate;
	private Date endDate;
	private Integer resourceStrength;
	private String projectCode;

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

	public Integer getResourceStrength() {
		return resourceStrength;
	}

	public void setResourceStrength(Integer resourceStrength) {
		this.resourceStrength = resourceStrength;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

}
