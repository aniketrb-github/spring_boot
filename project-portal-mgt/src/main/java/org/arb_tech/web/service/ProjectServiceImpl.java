package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Layer where we write the business logic for Projects
 * @author Aniket.Bharsakale
 */
@Service
public class ProjectServiceImpl implements IProjectService {
	
	@Autowired
	private IProjectRepo projectRepo;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}
}
