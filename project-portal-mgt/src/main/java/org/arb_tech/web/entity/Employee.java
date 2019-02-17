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
 * Employee entity which corresponds to table: tbl_employees in database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_employees")
public class Employee {
	private Integer id;
	private boolean deleted;
	private String name;
	private String designation;
	private String platform;
	private Project projectId;
	private String email;
	private Date joiningDate;

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

	@Column(name = "joining_date", nullable = false)
	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	public Employee() {}

	public Employee(Integer id, boolean deleted, String name, String designation, String platform, Project projectId,
			String email, Date joiningDate) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.name = name;
		this.designation = designation;
		this.platform = platform;
		this.projectId = projectId;
		this.email = email;
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", deleted=" + deleted + ", name=" + name + ", designation=" + designation
				+ ", platform=" + platform + ", projectId=" + projectId + ", email=" + email + ", joiningDate="
				+ joiningDate + "]";
	}

}
