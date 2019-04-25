package org.arb_tech.web.entity.base;

import javax.persistence.MappedSuperclass;

/**
 * SoftDelete acts as the Base Class
 * 
 * @author Aniket.Bharsakale
 */
@MappedSuperclass
public class SoftDelete extends BaseEntity {

	/**
	 * Default Version ID
	 */
	private static final long serialVersionUID = 1L;
	protected boolean deleted;

	// @Column(name = "deleted", nullable = false)
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
