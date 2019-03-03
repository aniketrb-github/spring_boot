package org.arb_tech.web.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageResolver {

	@Autowired
	private MessageSource messageSource;

	public String resolveLocalizedMessage(String messageCode) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(messageCode, new Object[] {}, currentLocale);
		return localizedErrorMessage;
	}
}
