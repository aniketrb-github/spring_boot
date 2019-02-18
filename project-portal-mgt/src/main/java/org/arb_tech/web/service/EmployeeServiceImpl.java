package org.arb_tech.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.arb_tech.web.dao.IEmployeeRepo;
import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service layer for employee CRUD operations
 * @author Aniket.Bharsakale
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepo employeeRepo;

	@Autowired
	private IProjectRepo projectRepo;

	@Override
	public Employee createEmployee(EmployeeVO empVO) throws ProjectPortalException {
		Project projectEntity = null;
		if (null != empVO) {

			// if user enters invalid projectCode we don't create an employee
			if (null != empVO.getProjectCode()) {
				projectEntity = projectRepo.getProjectByProjectCode(empVO.getProjectCode());

				if (null != projectEntity) {
					Employee emp = new Employee();
					emp.setName(empVO.getName());
					emp.setDesignation(empVO.getDesignation());
					emp.setEmail(empVO.getEmail());
					emp.setPlatform(empVO.getPlatform());
					emp.setProjectId(projectEntity);
					emp.setJoiningDate(empVO.getJoiningDate());
					employeeRepo.save(emp);
					return emp;
				} else {
					throw new ProjectPortalException("Error: Cannot create the desired employee!"
							+ "\nProjectCode:" + empVO.getProjectCode()+ " is null or not found in database."
							+ "\nProject code should be a valid code & the same Project should be available in database.");
				}
			} else {
				throw new ProjectPortalException(
						"Error: Cannot create the desired employee!" + "\nProject code cannot be null!");
			}
		} else {
			throw new ProjectPortalException("Error in creating the desired employee in database!"
					+ "\nEmployee Object cannot be null or empty.");
		}
	}

	@Override
	public List<EmployeeVO> getEmployees(Integer id) throws ProjectPortalException {
		List<Employee> empList = null;
		EmployeeVO employeeVO = null;
		if (null == id) {
			empList = employeeRepo.findAll();

			if (null != empList && !empList.isEmpty()) {
				List<EmployeeVO> empVOs = new ArrayList<>();

				empList.stream().forEach(e -> empVOs.add(new EmployeeVO(e.getName(), e.getDesignation(),
						e.getPlatform(), e.getProjectId().getProjectCode(), e.getEmail(), e.getJoiningDate())));

				System.out.println("using forEach():\n" + empVOs);

				empList.stream().map(e -> empVOs.add(new EmployeeVO(e.getName(), e.getDesignation(), e.getPlatform(),
						e.getProjectId().getProjectCode(), e.getEmail(), e.getJoiningDate())));

				System.out.println("using map():\n" + empVOs);
				return empVOs;
			} else {
				throw new ProjectPortalException("No Employees found in database.");
			}
		} else {
			Optional<Employee> empObject = employeeRepo.findById(id);

			if (empObject.isPresent()) {
				employeeVO = new EmployeeVO();
				employeeVO.setName(empObject.get().getName());
				employeeVO.setDesignation(empObject.get().getDesignation());
				employeeVO.setEmail(empObject.get().getEmail());
				employeeVO.setPlatform(empObject.get().getPlatform());
				employeeVO.setProjectCode(empObject.get().getProjectId().getProjectCode());
				employeeVO.setJoiningDate(empObject.get().getJoiningDate());
				return Arrays.asList(employeeVO);
			} else {
				throw new ProjectPortalException("Employee not found in database for employee ID: " + id);
			}
		}
	}

	@Override
	public Employee updateEmployeeById(Integer employeeId, EmployeeVO employeeVO) throws ProjectPortalException {
		if (null != employeeId) {
			Optional<Employee> empObject = employeeRepo.findById(employeeId);

			if (empObject.isPresent()) {
				Employee empEntity = empObject.get();
				if (null != employeeVO.getName())
					empEntity.setName(employeeVO.getName());

				if (null != employeeVO.getDesignation())
					empEntity.setDesignation(employeeVO.getDesignation());

				if (null != employeeVO.getEmail())
					empEntity.setEmail(employeeVO.getEmail());

				if (null != employeeVO.getPlatform())
					empEntity.setPlatform(employeeVO.getPlatform());
				
				if(null != employeeVO.getJoiningDate())
					empEntity.setJoiningDate(employeeVO.getJoiningDate());

				if (null != employeeVO.getProjectCode()) {

					if (!empEntity.getProjectId().getProjectCode().equals(employeeVO.getProjectCode())) {
						Project project = projectRepo.getProjectByProjectCode(employeeVO.getProjectCode());
						if (null != project) {
							empEntity.setProjectId(project);
						} else {
							throw new ProjectPortalException("Cannot Update the employee with requested project code."
									+ "Project code: " + employeeVO.getProjectCode() + " does not exist in database.");
						}
					}
				}

				Employee updatedEmployee = employeeRepo.save(empEntity);
				return updatedEmployee;

			} else {
				throw new ProjectPortalException(
						"Requested employee with employee ID: " + employeeId + " not found in database.");
			}
		} else {
			throw new ProjectPortalException("Error: Employee ID cannot be null or empty!");
		}
	}

	@Override
	public String deleteEmployeeById(Integer employeeId) throws ProjectPortalException {
		if (null != employeeId) {
			Optional<Employee> empObj = employeeRepo.findById(employeeId);
			if (empObj.isPresent()) {
				employeeRepo.delete(empObj.get());
				return "Employee with employeeId: " + employeeId + " deleted successfully.";
			} else {
				throw new ProjectPortalException("Employee with employeeId:" + employeeId + " not found in database.");
			}

		} else {
			throw new ProjectPortalException("Error: Employee ID cannot be null or empty!");
		}
	}
}
