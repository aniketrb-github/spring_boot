package org.arb_tech.web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_employee_metadata")
public class EmployeeMetadata {
	private Employee employeeId;
	private String username;
	private String password;

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
