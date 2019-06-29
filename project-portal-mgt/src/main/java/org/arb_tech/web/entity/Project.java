package org.arb_tech.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * Project entity which corresponds to table: tbl_projects in database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_projects")
@Where(clause = "deleted = 0")
public class Project {

	private Integer id;
	private String name;
	private boolean deleted;
	private Date startDate;
	private Date endDate;
	private Integer resourceStrength;
	private String projectCode;

	@Column(name = "project_code", nullable = false)
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

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

	@Column(name = "start_date", nullable = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", nullable = false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "resource_strength", nullable = false)
	public Integer getResourceStrength() {
		return resourceStrength;
	}

	public void setResourceStrength(Integer resourceStrength) {
		this.resourceStrength = resourceStrength;
	}	
	
}
