package org.arb_tech.web.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class represents the base time stamp entity for all the modules
 * @author Aniket Bharsakale
 */
@MappedSuperclass
public class BaseTimeStamp extends SoftDelete {

	/**
	 * Default Version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represent the created by user foreign key column of the entities.
	 */
	private Integer createdBy;

	/**
	 * Represent the last modified by user foreign key column of the entities.
	 */
	private Integer modifiedBy;

	/**
	 * Represent the created date column of the entities.
	 */
	private Date createdDate;

	/**
	 * Represent the modified date column of the entities.
	 */
	private Date modifiedDate;

	@Column(name = "created_by", nullable = false)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modified_by", nullable = false)
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
