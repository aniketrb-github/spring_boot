package org.arb_tech.web.service;

import org.arb_tech.web.dao.IBugRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aniket.Bharsakale
 */
@Service
public class BugService {
	
	@Autowired
	private IBugRepo bugRepo;
}
