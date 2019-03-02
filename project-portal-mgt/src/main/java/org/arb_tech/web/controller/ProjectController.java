package org.arb_tech.web.controller;

import org.arb_tech.web.service.IProjectService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.vo.ProjectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Controller where we receive & serve the requests from end-user &
 * return a valid response
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = ApplicationConstants.PROJECTS)
public class ProjectController {

	private static Logger log = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private IProjectService projectService;
	
	@GetMapping
	public @ResponseBody ResponseEntity<?> getProjects(
			@RequestParam(value = ApplicationConstants.PATH_VAR_ID, required = false) Integer id,
			@RequestParam(value = "code", required = false) String code) {
		
		log.info("<<< executing [ ProjectController -> getAllProjects() ] >>>");
		return projectService.getProjects(id, code);
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> createProject(@RequestBody ProjectVO projectVo) {

		log.info("<<< executing [ ProjectController -> createProject() ] >>>");
		return projectService.createProject(projectVo);
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateProjectById(@PathVariable Integer id, @RequestBody ProjectVO projectVO) {

		log.info("<<< executing [ ProjectController -> updateProjectById() ] >>>");
		return projectService.updateProjectById(id, projectVO);
	}

	@DeleteMapping(ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> deleteProjectById(@PathVariable Integer id) {

		log.info("<<< executing [ ProjectController -> deleteProjectById() ] >>>");
		return projectService.deleteProjectById(id);
	}
}