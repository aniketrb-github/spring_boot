package org.arb_tech.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.arb_tech.web.entity.base.SoftDelete;

/**
 * BugStatus entity which corresponds to table: tbl_bug_status in database
 * @author Aniket.Bharsakale
 */
@Entity
@Table(name = "tbl_bug_status")
public class BugStatus extends SoftDelete {
	private Integer id;
	private boolean deleted;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
