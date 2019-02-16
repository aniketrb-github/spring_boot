package org.arb_tech.web.entity.base;

/**
 * SoftDelete acts as the Base Class 
 * @author Aniket.Bharsakale
 */
public class SoftDelete extends BaseEntity {
	
	protected boolean deleted;

	// @Column(name = "deleted", nullable = false)
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
