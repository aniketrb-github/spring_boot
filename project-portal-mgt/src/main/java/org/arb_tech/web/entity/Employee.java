package org.arb_tech.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.arb_tech.web.entity.base.SoftDelete;

/**
 * Employee Entity which corresponds to "tbl_employees" in Database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_employees")
public class Employee extends SoftDelete {
	private String name;
	private String designation;
	private String platform;
	private Project projectId;
	private String email;

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "designation", nullable = false)
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "platform", nullable = true)
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
