package org.arb_tech.web.service;

import java.util.List;

import org.arb_tech.web.dao.IEmployeeRepo;
import org.arb_tech.web.entity.Employee;
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

	@Override
	public String createEmployee(EmployeeVO empVO) throws ProjectPortalException {
		if(null != empVO) {
			Employee emp = new Employee();
			emp.setName(empVO.getName());
			emp.setDesignation(empVO.getDesignation());
			emp.setEmail(empVO.getEmail());
			emp.setPlatform(empVO.getPlatform());
			// emp.setProjectId(projectId);
		}
		return null;
	}

	@Override
	public List<EmployeeVO> getAllEmployees() throws ProjectPortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeVO getEmployeeById(Integer employeeId) throws ProjectPortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateEmployeeById(Integer employeeId, EmployeeVO employeeVO) throws ProjectPortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEmployeeById(Integer employeeId) throws ProjectPortalException {
		// TODO Auto-generated method stub
		return null;
	}
}

