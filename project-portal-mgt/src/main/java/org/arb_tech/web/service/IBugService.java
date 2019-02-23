package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.entity.Bug;
import org.arb_tech.web.vo.BugVO;
/**
 * Interface for Service layer implementing loose coupling in application
 *
 * @author Aniket.Bharsakale
 */
public interface IBugService {
	
	public List<Bug> getBugs(Integer statusId, Integer taskId, Integer assigneeId, Integer reporterId, String projectCode);
	
	public Bug createBug(BugVO bugVO);
	
	public Bug updateBug(Integer bugId, BugVO bugVO);
	
	public String deleteBug(Integer bugId);
}
