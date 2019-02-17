package org.arb_tech.web.vo;

import java.util.Date;

/**
 * EmployeeVO is a view object, used as a view layer object
 * @author Aniket.Bharsakale
 */
public class EmployeeVO {
	private String name;
	private String designation;
	private String platform;
	private String projectCode;
	private String email;
	private Date joiningDate;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	public EmployeeVO() { }

	public EmployeeVO(String name, String designation, String platform, String projectCode, String email,
			Date joiningDate) {
		super();
		this.name = name;
		this.designation = designation;
		this.platform = platform;
		this.projectCode = projectCode;
		this.email = email;
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "EmployeeVO [name=" + name + ", designation=" + designation + ", platform=" + platform + ", projectCode="
				+ projectCode + ", email=" + email + ", joiningDate=" + joiningDate + "]";
	}
}
