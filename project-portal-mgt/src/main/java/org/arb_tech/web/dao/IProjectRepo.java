package org.arb_tech.web.dao;

import org.arb_tech.web.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IProjectRepo which provides all the CRUD implementations to Project entity
 * @author Aniket.Bharsakale
 */
public interface IProjectRepo extends JpaRepository<Project, Integer> {

}
