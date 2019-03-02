package org.arb_tech.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.arb_tech.web.dao.IEmployeeRepo;
import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.entity.Employee;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectException;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private MessageResolver msgResolver;
	
	@Autowired
	private IProjectRepo projectRepo;

	@Override
	public ResponseEntity<?> createEmployee(EmployeeVO empVO) throws ProjectException {
		Project projectEntity = null;
		Employee employee = null;
		ResponseEntity<?> response = null;
		if (null != empVO) {

			// if user enters invalid projectCode we don't create an employee
			if (null != empVO.getProjectCode()) {
				projectEntity = projectRepo.getProjectByProjectCode(empVO.getProjectCode());

				if (null != projectEntity) {
					employee = new Employee();
					employee.setName(empVO.getName());
					employee.setDesignation(empVO.getDesignation());
					employee.setEmail(empVO.getEmail());
					employee.setPlatform(empVO.getPlatform());
					employee.setProjectId(projectEntity);
					employee.setJoiningDate(empVO.getJoiningDate());
					employee = employeeRepo.save(employee);
					response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
							Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), employee, null));
				} else {
					response = ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.PROJECT_NOT_FOUND,
									msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
				}
			} else {
				throw new ProjectException(Messages.PROJECT_CODE_NULL, HttpStatus.NOT_FOUND.toString(),
						msgResolver.resolveLocalizedMessage(Messages.PROJECT_CODE_NULL));
			}
		} else {
			throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.EMP_VO_NULL));
		}
		return response;
	}

	@Override
	public ResponseEntity<?> getEmployees(Integer id) throws ProjectException {
		ResponseEntity<?> response = null;
		List<Employee> empList = null;
		List<EmployeeVO> empVoList = null;
		EmployeeVO employeeVO = null;

		if (null == id) {
			empList = employeeRepo.findAll();

			if (null != empList && !empList.isEmpty()) {
				List<EmployeeVO> empVOs = new ArrayList<>();

				empList.stream().forEach(e -> empVOs.add(new EmployeeVO(e.getName(), e.getDesignation(),
						e.getPlatform(), e.getProjectId().getProjectCode(), e.getEmail(), e.getJoiningDate())));

				/*
				 empList.stream().map(e -> empVOs.add(new EmployeeVO(e.getName(),
				 e.getDesignation(), e.getPlatform(), e.getProjectId().getProjectCode(),
				 e.getEmail(), e.getJoiningDate())));
				 */

				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), empVOs, null));
			} else {
				throw new ProjectException(msgResolver.resolveLocalizedMessage(Messages.NO_EMP_RECORDS));
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
				empVoList = Arrays.asList(employeeVO);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), empVoList, null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(),
						Messages.EMP_NOT_FOUND, msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), empVoList, null));
			}
		}
		return response;
	}

	@Override
	public ResponseEntity<?> updateEmployeeById(Integer empId, EmployeeVO employeeVO) throws ProjectException {
		ResponseEntity<?> response = null;
		if (null != empId || null != employeeVO) {
			Optional<Employee> empObject = employeeRepo.findById(empId);

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

				if (null != employeeVO.getJoiningDate())
					empEntity.setJoiningDate(employeeVO.getJoiningDate());

				if (null != employeeVO.getProjectCode()) {

					if (!empEntity.getProjectId().getProjectCode().equals(employeeVO.getProjectCode())) {
						Project project = projectRepo.getProjectByProjectCode(employeeVO.getProjectCode());
						if (null != project) {
							empEntity.setProjectId(project);
						} else {
							response = ResponseEntity.status(HttpStatus.NOT_FOUND)
									.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(),
											Messages.PROJECT_NOT_FOUND,
											msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
						}
					}
				}

				Employee updatedEmpEntity = employeeRepo.save(empEntity);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), updatedEmpEntity, null));

			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.EMP_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), null, null));
			}
		} else {
			throw new ProjectException(msgResolver.resolveLocalizedMessage(Messages.EMP_ID_NULL));
		}
		return response;
	}

	@Override
	public ResponseEntity<?> deleteEmployeeById(Integer employeeId) throws ProjectException {
		ResponseEntity<?> response = null;
		if (null != employeeId) {
			Optional<Employee> empObj = employeeRepo.findById(employeeId);
			if (empObj.isPresent()) {
				employeeRepo.delete(empObj.get());
				response = ResponseEntity.status(HttpStatus.OK)
						.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MSG_OK,
								msgResolver.resolveLocalizedMessage(Messages.MSG_OK),
								msgResolver.resolveLocalizedMessage(Messages.EMP_DELETE_SUCCESS), null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.EMP_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.EMP_NOT_FOUND), null, null));
			}

		} else {
			throw new ProjectException(msgResolver.resolveLocalizedMessage(Messages.EMP_ID_NULL));
		}
		return response;
	}
}
