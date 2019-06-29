package org.arb_tech.web.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.arb_tech.web.dao.IProjectRepo;
import org.arb_tech.web.entity.Project;
import org.arb_tech.web.exception.ProjectPortalException;
import org.arb_tech.web.util.JsonResponse;
import org.arb_tech.web.util.MessageResolver;
import org.arb_tech.web.util.Messages;
import org.arb_tech.web.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * service layer which contains the business logic for projects
 * 
 * @author Aniket.Bharsakale
 */
@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepo projectRepo;

	@Autowired
	private MessageResolver msgResolver;

	public ResponseEntity<?> getProjects(Integer projectId, String projectCode) {
		ResponseEntity<?> response = null;
		List<Project> projectsList = null;

		Project project = null;
		if (null == projectId && null == projectCode) {
			// GET ALL PROJECTS
			projectsList = projectRepo.findAll();
			response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
					Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), projectsList));
		} else if (null != projectId) {

			// GET PROJECT BY ID
			Optional<Project> object = projectRepo.findById(projectId);

			if (object.isPresent()) {
				projectsList = Arrays.asList(object.get());
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), projectsList));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.PROJECT_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
			}
		} else {
			// GET PROJECT BY CODE
			project = projectRepo.getProjectByProjectCode(projectCode);
			if (null != project) {
				projectsList = Arrays.asList(project);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), projectsList));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.PROJECT_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
			}
		}
		return response;
	}

	@Override
	public ResponseEntity<?> createProject(ProjectVO projectVO) throws ProjectPortalException {
		ResponseEntity<?> response = null;
		Project project = null;

		if (null != projectVO) {
			project = new Project();
			project.setName(projectVO.getName());
			project.setResourceStrength(projectVO.getResourceStrength());
			project.setStartDate(projectVO.getStartDate());
			project.setEndDate(projectVO.getEndDate());
			project.setProjectCode(projectVO.getProjectCode());
			project = projectRepo.save(project);
			response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
					Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), project));
		} else {
			throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.PROJECT_VO_NULL));
		}
		return response;
	}

	@Override
	public ResponseEntity<?> updateProjectById(Integer projectId, ProjectVO projectVO) throws ProjectPortalException {
		ResponseEntity<?> response = null;
		Project project = null;

		if (null != projectId && null != projectVO) {
			Optional<Project> object = projectRepo.findById(projectId);
			if (object.isPresent()) {
				project = object.get();
				if (null != projectVO.getName())
					project.setName(projectVO.getName());
				if (null != projectVO.getStartDate())
					project.setStartDate(projectVO.getStartDate());
				if (null != projectVO.getEndDate())
					project.setEndDate(projectVO.getEndDate());
				if (null != projectVO.getResourceStrength())
					project.setResourceStrength(projectVO.getResourceStrength());
				if (null != projectVO.getProjectCode())
					project.setProjectCode(projectVO.getProjectCode());

				project = projectRepo.save(project);
				response = ResponseEntity.status(HttpStatus.OK).body(JsonResponse.instance(HttpStatus.OK.value(),
						Messages.MSG_OK, msgResolver.resolveLocalizedMessage(Messages.MSG_OK), project));
			}
		} else {
			if (null == projectVO)
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.PROJECT_VO_NULL));
			else
				throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.PROJECT_ID_NULL));
		}
		return response;
	}

	@Override
	public ResponseEntity<?> deleteProjectById(Integer projectId) throws ProjectPortalException {
		ResponseEntity<?> response = null;
		if (null != projectId) {
			Optional<Project> object = projectRepo.findById(projectId);
			if (object.isPresent()) {
				// Soft Deleting the Entity
				object.get().setDeleted(Boolean.TRUE);
				projectRepo.save(object.get());
				response = ResponseEntity.status(HttpStatus.OK)
						.body(JsonResponse.instance(HttpStatus.OK.value(), Messages.MSG_OK,
								msgResolver.resolveLocalizedMessage(Messages.MSG_OK),
								msgResolver.resolveLocalizedMessage(Messages.PROJECT_DELETE_SUCCESS), null));
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(JsonResponse.instance(HttpStatus.NOT_FOUND.value(), Messages.PROJECT_NOT_FOUND,
								msgResolver.resolveLocalizedMessage(Messages.PROJECT_NOT_FOUND)));
			}
		} else {
			throw new ProjectPortalException(msgResolver.resolveLocalizedMessage(Messages.PROJECT_ID_NULL));
		}
		return response;
	}
}
