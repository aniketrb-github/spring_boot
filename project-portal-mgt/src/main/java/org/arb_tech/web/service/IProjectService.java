package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.vo.ProjectVO;

/**
 * interface introduced for more loose coupling in the application at service
 * layer at the time of bean injection, spring container looks up for the class
 * which is implementing this interface & creates an bean of it & injects it
 * 
 * @author Aniket.Bharsakale
 */
public interface IProjectService {
	public List<Project> getProjects(Integer projectId, String projectCode) throws ProjectPortalException;

	public String createProject(ProjectVO projectVO) throws ProjectPortalException;
	
	public String updateProjectById(Integer projectId, ProjectVO projectVO) throws ProjectPortalException;
	
	public String deleteProjectById(Integer projectId) throws ProjectPortalException;
	
}
