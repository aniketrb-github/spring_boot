package org.arb_tech.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * Status entity which corresponds to table: tbl_status in database
 * OPEN, IN PROGRESS, RESOLVED, CLOSED
 * @author Aniket.Bharsakale
 */

@Entity
@Table(name = "tbl_status")
@Where(clause = "deleted = 0")
public class Status {
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}