package org.arb_tech.web.controller;

import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.service.IEmployeeService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.vo.EmployeeVO;
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
 * employee controller to handle request & responses
 * 
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = ApplicationConstants.EMPLOYEES)
public class EmployeeController {

	private static Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping
	public @ResponseBody ResponseEntity<?> createEmployee(@RequestBody EmployeeVO employeeVO) throws ProjectException {

		log.info("<<< executing [ EmployeeController -> createEmployee() ] >>>");
		return employeeService.createEmployee(employeeVO);
	}

	@GetMapping
	public @ResponseBody ResponseEntity<?> getEmployees(@RequestParam(value = "id", required = false) Integer id)
			throws ProjectException {

		log.info("<<< executing [ EmployeeController -> getEmployees() ] >>>");
		return employeeService.getEmployees(id);
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateEmployeeById(@PathVariable Integer id,
			@RequestBody EmployeeVO employeeVO) throws ProjectException {

		log.info("<<< executing [ EmployeeController -> updateEmployeeById() ] >>>");
		return employeeService.updateEmployeeById(id, employeeVO);
	}

	@DeleteMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id) throws ProjectException {

		log.info("<<< executing [ EmployeeController -> deleteEmployeeById() ] >>>");
		return employeeService.deleteEmployeeById(id);
	}

}