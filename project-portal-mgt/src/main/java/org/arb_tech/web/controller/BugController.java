package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Bug;
import org.arb_tech.web.service.IBugService;
import org.arb_tech.web.vo.BugVO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/bugs")
public class BugController {

	@Autowired
	private IBugService bugService;

	@GetMapping
	public @ResponseBody List<Bug> getBugs(@RequestParam(name = "statusId", required = false) Integer statusId,
			@RequestParam(name = "taskId", required = false) Integer taskId,
			@RequestParam(name = "assigneeId", required = false) Integer assigneeId,
			@RequestParam(name = "reporterId", required = false) Integer reporterId,
			@RequestParam(name = "projectCode", required = false) String projectCode) {

		return bugService.getBugs(statusId, taskId, assigneeId, reporterId, projectCode);
	}

	@PostMapping
	public @ResponseBody Bug createBug(@RequestBody BugVO bugVO) {
		return bugService.createBug(bugVO);
	}

	@PutMapping(path = "/{bugId}")
	public @ResponseBody Bug updateBug(@PathVariable Integer bugId, @RequestBody BugVO bugVO) {
		return bugService.updateBug(bugId, bugVO);
	}

	@DeleteMapping(path = "/{bugId}")
	public String deleteBug(@PathVariable Integer bugId) {
		return bugService.deleteBug(bugId);
	}

}
