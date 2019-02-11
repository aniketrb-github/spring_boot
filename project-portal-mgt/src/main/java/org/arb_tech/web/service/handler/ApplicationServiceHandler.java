package org.arb_tech.web.service.handler;

import java.util.Map;

import org.arb_tech.web.service.IProjectService;
import org.arb_tech.web.util.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationServiceHandler implements IApplicationServiceHandler {

	@Autowired
	private IProjectService projectService;

	@Override
	public Object process(ApplicationConstants.ACTION appConstant, Map<String, String> params) {

		switch (appConstant) {
		case GET_ALL_PROJECTS:

			break;

		case GET_PROJECT:

			break;
		case CREATE_PROJECT:

			break;
		
		/*case GET_ALL_PROJECTS:

			break;
		case GET_ALL_PROJECTS:

			break;*/

		default:
			break;
		}
		return null;
	}

}
