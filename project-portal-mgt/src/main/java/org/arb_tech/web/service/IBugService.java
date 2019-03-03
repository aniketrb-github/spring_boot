package org.arb_tech.web.service;

import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.vo.BugVO;
import org.springframework.http.ResponseEntity;

/**
 * Interface for Service layer implementing loose coupling in application
 *
 * @author Aniket.Bharsakale
 */
public interface IBugService {

	public ResponseEntity<?> getBugs(Integer statusId, Integer taskId, Integer assigneeId, Integer reporterId,
			String projectCode) throws ProjectException;

	public ResponseEntity<?> createBug(BugVO bugVO) throws ProjectException;

	public ResponseEntity<?> updateBug(Integer bugId, BugVO bugVO) throws ProjectException;

	public ResponseEntity<?> deleteBug(Integer bugId) throws ProjectException;
}
