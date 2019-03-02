package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Bug;
import org.arb_tech.web.service.IBugService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.BugVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@Autowired
	private MessageResolver msgResolver;
    
	@GetMapping
	public @ResponseBody ResponseEntity<?> getBugs(@RequestParam(name = "statusId", required = false) Integer statusId,
			@RequestParam(name = "taskId", required = false) Integer taskId,
			@RequestParam(name = "assigneeId", required = false) Integer assigneeId,
			@RequestParam(name = "reporterId", required = false) Integer reporterId,
			@RequestParam(name = "projectCode", required = false) String projectCode) {
		log.info("<<< executing [ BugController -> getBugs() ] >>>");
		
		List<Bug> bugsList = bugService.getBugs(statusId, taskId, assigneeId, reporterId, projectCode);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK,
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), bugsList)); 
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> createBug(@RequestBody BugVO bugVO) {
		log.info("<<< executing [ BugController -> createBug() ] >>>");
		
		Bug bug = bugService.createBug(bugVO);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK,
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), bug));
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateBug(@PathVariable Integer id, @RequestBody BugVO bugVO) {
		log.info("<<< executing [ BugController -> updateBug() ] >>>");
		
		Bug bug = bugService.updateBug(id, bugVO);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK,
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), bug));
	}

	@DeleteMapping(path = ApplicationConstants.PATH_VAR_ID)
	public ResponseEntity<?> deleteBug(@PathVariable Integer id) {
		log.info("<<< executing [ BugController -> deleteBug() ] >>>");
		
		String response = bugService.deleteBug(id);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK,
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), response));
	}

}
