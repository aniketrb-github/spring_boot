package org.arb_tech.web.entity.base;

/**
 * BaseEntity acts as the Base Class
 * @author Aniket.Bharsakale
 */
public class BaseEntity {
	protected Integer id;

	// @Id 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
