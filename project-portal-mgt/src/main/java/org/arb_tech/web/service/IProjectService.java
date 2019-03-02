package org.arb_tech.web.service;

import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.vo.ProjectVO;
import org.springframework.http.ResponseEntity;

/**
 * interface introduced for more loose coupling in the application at service
 * layer at the time of bean injection, spring container looks up for the class
 * which is implementing this interface & creates an bean of it & injects it
 * 
 * @author Aniket.Bharsakale
 */
public interface IProjectService {
	public ResponseEntity<?> getProjects(Integer projectId, String projectCode) throws ProjectPortalException;

	public ResponseEntity<?> createProject(ProjectVO projectVO) throws ProjectPortalException;

	public ResponseEntity<?> updateProjectById(Integer projectId, ProjectVO projectVO) throws ProjectPortalException;

	public ResponseEntity<?> deleteProjectById(Integer projectId) throws ProjectPortalException;

}
