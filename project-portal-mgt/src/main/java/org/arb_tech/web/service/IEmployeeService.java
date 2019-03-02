package org.arb_tech.web.service;

import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.vo.EmployeeVO;
import org.springframework.http.ResponseEntity;

/**
 * @author Aniket.Bharsakale
 */
public interface IEmployeeService {

	public ResponseEntity<?> createEmployee(EmployeeVO employeeVO) throws ProjectException;

	public ResponseEntity<?> getEmployees(Integer id) throws ProjectException;

	public ResponseEntity<?> updateEmployeeById(Integer employeeId, EmployeeVO employeeVO) throws ProjectException;

	public ResponseEntity<?> deleteEmployeeById(Integer employeeId) throws ProjectException;
}
