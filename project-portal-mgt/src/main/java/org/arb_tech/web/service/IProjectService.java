package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectPortalException;

/**
 * interface introduced for more loose coupling in the application at service layer
 * at the time of bean injection, spring container looks up for the class
 * which is implementing this interface & creates an bean of it & injects it
 * @author Aniket.Bharsakale
 */
public interface IProjectService {
	public List<Project> getAllProjects() throws ProjectPortalException;
}
