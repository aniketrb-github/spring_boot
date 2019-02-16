package org.arb_tech.web.controller;

import org.arb_tech.web.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * employee controller to handle request & responses
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	// create 
	
	// getAll
	
	// getOne 
	
	// update
	
	// delete
}
