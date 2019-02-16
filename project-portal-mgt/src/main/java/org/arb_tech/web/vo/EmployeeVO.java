package org.arb_tech.web.vo;

import org.arb_tech.web.entity.Project;

/**
 * EmployeeVO is a view object, used as a view layer object
 * @author Aniket.Bharsakale
 */
public class EmployeeVO {
	private String name;
	private String designation;
	private String platform;
	private Project projectId;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
