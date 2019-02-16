package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Project;
import org.arb_tech.web.service.IProjectService;
import org.arb_tech.web.vo.ProjectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	private IProjectService projectService;
	
	@GetMapping
	public @ResponseBody List<Project> getAllProjects() {
		log.info("<<<< executing [ ProjectController -> getAllProjects() ] >>>>");
		return projectService.getAllProjects();
	}
	
	@PostMapping
	public @ResponseBody String createProject (@RequestBody ProjectVO projectVo) {
		return projectService.createProject(projectVo);
	}
	
	@GetMapping(path = "/{projectId}")
	public @ResponseBody ProjectVO getProjectById(@PathVariable Integer projectId) {
		return projectService.getProjectById(projectId);
	}
	
	@PutMapping(path = "/{projectId}")
	public @ResponseBody String updateProjectById(@PathVariable Integer projectId, @RequestBody ProjectVO projectVO) {
		return projectService.updateProjectById(projectId, projectVO);
	}
	
	@DeleteMapping("/{projectId}")
	public @ResponseBody String deleteProjectById(@PathVariable Integer projectId) {
		return projectService.deleteProjectById(projectId);
	}
}
