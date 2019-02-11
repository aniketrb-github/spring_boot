package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Project;
import org.arb_tech.web.service.IProjectService;
import org.arb_tech.web.service.handler.IApplicationServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Controller where we receive & serve the requests from end-user 
 * & return a valid response
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = "/projects")
public class ProjectController {
	
	private static Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	// temporary injection | later use the ApplicationServiceHandler object 
	// Controllers(actions) -> ApplicationServiceHandler -> ServiceLayer
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IApplicationServiceHandler appService;
	
	@GetMapping
	public @ResponseBody List<Project> getAllProjects() {
		//return appService.process(ACTION.GET_ALL_PROJECTS, null);

		log.info("<<<<[ Executing ProjectController -> getAllProjects() ]>>>>");
		return projectService.getAllProjects();
	}
}
