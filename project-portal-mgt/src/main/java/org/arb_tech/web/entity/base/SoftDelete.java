package org.arb_tech.web.entity.base;

/**
 *
 * @author Aniket.Bharsakale
 */
public class SoftDelete extends BaseEntity {
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
