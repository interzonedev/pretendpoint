package com.interzonedev.pretendpoint.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

public abstract class PretendPointController {

	protected Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@ResponseBody
	@ExceptionHandler(Throwable.class)
	protected ErrorResponse handleThrowable(Throwable t, HttpServletResponse response) {

		log.error("handleThrowable", t);

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		String stackTrace = ExceptionUtils.getStackTrace(t);

		ErrorResponse errorResponse = new ErrorResponse(stackTrace);

		return errorResponse;

	}
}
