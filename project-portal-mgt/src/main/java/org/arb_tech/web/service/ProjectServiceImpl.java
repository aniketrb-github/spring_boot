package org.arb_tech.web.service;

import java.util.List;
import java.util.Optional;

import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service layer which contains the business logic for projects
 * @author Aniket.Bharsakale
 */
@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepo projectRepo;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	@Override
	public String createProject(ProjectVO projectVO) throws ProjectPortalException {
		Project project = null;
		if (null != projectVO) {
			project = new Project();
			project.setName(projectVO.getName());
			project.setResourceStrength(projectVO.getResourceStrength());
			project.setStartDate(projectVO.getStartDate());
			project.setEndDate(projectVO.getEndDate());
			project = projectRepo.save(project);
		} else {
			throw new ProjectPortalException("Project to be created cannot be null.");
		}

		if (null != project)
			return "Project: " + project.getName() + " created & saved in database.";
		else
			return "Project not created in database.";
	}

	@Override
	public ProjectVO getProjectById(Integer projectId) throws ProjectPortalException {
		ProjectVO project = null;
		if (null != projectId) {
			Optional<Project> object = projectRepo.findById(projectId);

			if (object.isPresent()) {
				project = new ProjectVO();
				project.setName(object.get().getName());
				project.setResourceStrength(object.get().getResourceStrength());
				project.setStartDate(object.get().getStartDate());
				project.setEndDate(object.get().getEndDate());
				return project;
			} else
				return null;
		} else {
			throw new ProjectPortalException("Project ID cannot be null.");
		}
	}

	@Override
	public String updateProjectById(Integer projectId, ProjectVO projectVO) throws ProjectPortalException {
		if (null != projectId && null != projectVO) {
			Optional<Project> object = projectRepo.findById(projectId);
			if (object.isPresent()) {
				Project project = object.get();
				if (null != projectVO.getName())
					project.setName(projectVO.getName());
				if (null != projectVO.getStartDate())
					project.setStartDate(projectVO.getStartDate());
				if (null != projectVO.getEndDate())
					project.setEndDate(projectVO.getEndDate());
				if (null != projectVO.getResourceStrength())
					project.setResourceStrength(projectVO.getResourceStrength());
				projectRepo.save(project);
				return "Project Info. updated successfully in database.";
			} else
				return "Project with ID:" + projectId + " is not present/is empty in database. ";
		} else {
			throw new ProjectPortalException("ProjecId or the Project tobe updated cannot be NULL.");
		}
	}

	@Override
	public String deleteProjectById(Integer projectId) throws ProjectPortalException {
		if (null != projectId) {
			Optional<Project> object = projectRepo.findById(projectId);
			if (object.isPresent()) {
				projectRepo.delete(object.get());
				return "Project: " + object.get().getName() + "has been deleted from database.";
			} else {
				return "Any project with an ID:" + projectId + " not found in database.";
			}
		} else {
			throw new ProjectPortalException("Project ID cannot be null.");
		}
	}
}
