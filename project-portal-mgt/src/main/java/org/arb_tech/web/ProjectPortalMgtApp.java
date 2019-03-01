package org.arb_tech.web;

import org.arb_tech.web.util.ApplicationConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Entry point of this web based application
 * @author Aniket.Bharsakale
 */
@SpringBootApplication
public class ProjectPortalMgtApp {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPortalMgtApp.class, args);
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(ApplicationConstants.MESSAGES);
		messageSource.setDefaultEncoding(ApplicationConstants.UTF8_CHARSET_NAME);
		return messageSource;
	}
}

