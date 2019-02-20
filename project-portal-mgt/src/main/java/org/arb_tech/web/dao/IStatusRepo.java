package org.arb_tech.web.dao;

import org.arb_tech.web.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Eliminates the need of defining your own StatusDAOImpl class
 * Most common & standard DAO operations are implemented by spring boot itself
 * @author Aniket.Bharsakale
 */
@Repository
public interface IStatusRepo extends JpaRepository<Status, Integer> {
	public Status getStatusByName(String name);
}
