package org.arb_tech.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arb_tech.web.entity.base.SoftDelete;

/**
 * Project Entity which corresponds to "tbl_projects" in Database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_projects")
public class Project extends SoftDelete {

	private String name;

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
