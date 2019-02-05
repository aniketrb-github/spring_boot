package org.arb_tech.web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.arb_tech.web.entity.base.SoftDelete;

/**
 *
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_projects")
public class Project extends SoftDelete {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
