package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.service.IEmployeeService;
import org.arb_tech.web.util.ApplicationConstants;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.EmployeeVO;
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
	
	@Autowired
	private MessageResolver msgResolver;

	@PostMapping
	public @ResponseBody ResponseEntity<?> createEmployee(@RequestBody EmployeeVO employeeVO) {
		log.info("<<< executing [ EmployeeController -> createEmployee() ] >>>");
		
		Employee emp = employeeService.createEmployee(employeeVO);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), emp, null));
	}

	@GetMapping
	public @ResponseBody ResponseEntity<?> getEmployees(@RequestParam(value = "id", required = false) Integer id) {
		log.info("<<< executing [ EmployeeController -> getEmployees() ] >>>");
		
		List<EmployeeVO> empVoList =  employeeService.getEmployees(id);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), empVoList, null));
	}

	@PutMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeeVO employeeVO) {
		log.info("<<< executing [ EmployeeController -> updateEmployeeById() ] >>>");
		
		Employee emp = employeeService.updateEmployeeById(id, employeeVO);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), emp, null));
	}

	@DeleteMapping(path = ApplicationConstants.PATH_VAR_ID)
	public @ResponseBody ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id) {
		log.info("<<< executing [ EmployeeController -> deleteEmployeeById() ] >>>");
		
		String response = employeeService.deleteEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MESSAGE_OK, 
				msgResolver.resolveLocalizedMessage(Messages.MESSAGE_OK), response, null));
	}

}