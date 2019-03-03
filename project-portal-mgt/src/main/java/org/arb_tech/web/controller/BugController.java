package org.arb_tech.web.controller;

import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.service.IBugService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.vo.BugVO;
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
 * Controller Layer for Bug Resource
 * 
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = ApplicationConstants.BUGS)
public class BugController {

	private static Logger log = LoggerFactory.getLogger(BugController.class);

	@Autowired
	private IBugService bugService;

	@GetMapping
	public @ResponseBody ResponseEntity<?> getBugs(
			@RequestParam(name = ApplicationConstants.STATUS_ID, required = false) Integer statusId,
			@RequestParam(name = ApplicationConstants.TASK_ID, required = false) Integer taskId,
			@RequestParam(name = ApplicationConstants.ASSIGNEE_ID, required = false) Integer assigneeId,
			@RequestParam(name = ApplicationConstants.REPORTER_ID, required = false) Integer reporterId,
			@RequestParam(name = ApplicationConstants.PROJECT_CODE, required = false) String projectCode)
			throws ProjectException {

		log.info("<<< executing [ BugController -> getBugs() ] >>>");
		return bugService.getBugs(statusId, taskId, assigneeId, reporterId, projectCode);
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> createBug(@RequestBody BugVO bugVO) throws ProjectException {

		log.info("<<< executing [ BugController -> createBug() ] >>>");
		return bugService.createBug(bugVO);
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateBug(@PathVariable Integer id, @RequestBody BugVO bugVO)
			throws ProjectException {

		log.info("<<< executing [ BugController -> updateBug() ] >>>");
		return bugService.updateBug(id, bugVO);
	}

	@DeleteMapping(path = ApplicationConstants.PATH_VAR_ID)
	public ResponseEntity<?> deleteBug(@PathVariable Integer id) throws ProjectException {

		log.info("<<< executing [ BugController -> deleteBug() ] >>>");
		return bugService.deleteBug(id);
	}

}
