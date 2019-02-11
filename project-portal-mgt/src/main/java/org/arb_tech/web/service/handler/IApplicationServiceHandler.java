package org.arb_tech.web.service.handler;

import java.util.Map;

import org.arb_tech.web.util.ApplicationConstants;

public interface IApplicationServiceHandler {
	public Object process(ApplicationConstants.ACTION action, Map<String, String> params);
}
