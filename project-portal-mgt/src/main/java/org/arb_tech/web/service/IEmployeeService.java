package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.vo.EmployeeVO;

/**
 * @author Aniket.Bharsakale
 */
public interface IEmployeeService {

	public Employee createEmployee(EmployeeVO employeeVO) throws ProjectPortalException;

	public List<EmployeeVO> getAllEmployees() throws ProjectPortalException;

	public EmployeeVO getEmployeeById(Integer employeeId) throws ProjectPortalException;

	public Employee updateEmployeeById(Integer employeeId, EmployeeVO employeeVO) throws ProjectPortalException;

	public String deleteEmployeeById(Integer employeeId) throws ProjectPortalException;
}
