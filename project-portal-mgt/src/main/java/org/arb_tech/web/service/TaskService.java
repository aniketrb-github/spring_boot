package org.arb_tech.web.service;

import org.arb_tech.web.dao.ITaskRepo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aniket.Bharsakale
 */
public class TaskService {

	@Autowired
	private ITaskRepo taskRepo;
}
