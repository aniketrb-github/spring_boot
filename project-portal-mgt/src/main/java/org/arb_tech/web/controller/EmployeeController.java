package org.arb_tech.web.controller;

import java.util.List;

import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.service.IEmployeeService;
import org.arb_tech.web.vo.EmployeeVO;
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
 * employee controller to handle request & responses
 * 
 * @author Aniket.Bharsakale
 */
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping
	public @ResponseBody Employee createEmployee(@RequestBody EmployeeVO employeeVO) {
		return employeeService.createEmployee(employeeVO);
	}

	@GetMapping
	public @ResponseBody List<EmployeeVO> getEmployees(@RequestParam(value = "id", required = false) Integer id) {
		return employeeService.getEmployees(id);
	}

	@PutMapping(path = "/{id}")
	public @ResponseBody Employee updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeeVO employeeVO) {
		return employeeService.updateEmployeeById(id, employeeVO);
	}

	@DeleteMapping(path = "/{id}")
	public @ResponseBody String deleteEmployeeById(@PathVariable Integer id) {
		return employeeService.deleteEmployeeById(id);
	}

}
