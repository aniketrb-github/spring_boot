package org.arb_tech.web.dao;

import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectPortalException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * IProjectRepo which provides all the CRUD implementations to Project entity
 * @author Aniket.Bharsakale
 */
@Repository
public interface IProjectRepo extends JpaRepository<Project, Integer> {
	public Project getProjectByProjectCode(String projectCode) throws ProjectPortalException;
}
