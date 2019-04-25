package org.arb_tech.web.entity.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BaseEntity acts as the Base Class
 * 
 * @author Aniket.Bharsakale
 */
@SuppressWarnings("rawtypes")
@MappedSuperclass
public class BaseEntity implements Serializable, Comparable {
	/**
	 * Default Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer version = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "version_id", nullable = false)
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Overridden object's equals method.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}

		final BaseEntity other = (BaseEntity) obj;

		if (null == this.getId() || null == other.getId()) {
			return false;
		}
		return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();

	}

	/**
	 * Overridden object's hashCode method.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) obj;
			if (this.id == entity.id)
				return 0;
			else if (this.id > entity.id)
				return 1;
			else
				return -1;
		}
		return 0;
	}
}
