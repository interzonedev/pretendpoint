package com.interzonedev.pretendpoint.web;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class PretendPointRequestListener implements ServletRequestListener {

	private static final String newline = System.getProperty("line.separator", "\n");

	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	public void requestInitialized(ServletRequestEvent event) {

		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();

		log.debug(getRequestBoundaryLogMessage(request, 14, "Received"));

		log.debug(getRequestParametersLogMessage(request));

		log.debug(getRequestHeadersLogMessage(request));

		log.debug(getRequestCookiesLogMessage(request));

		log.debug(getRequestBoundaryLogMessage(request, 7, "Starting"));

	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {

		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();

		log.debug(getRequestBoundaryLogMessage(request, 14, "Ending"));

	}

	private String getRequestBoundaryLogMessage(HttpServletRequest request, int bannerLength, String title) {
		StringBuilder logMessage = new StringBuilder();

		logMessage.append(StringUtils.repeat("*", bannerLength)).append(" ").append(title).append(" (")
				.append(request.hashCode()).append("): ").append(request.getMethod()).append(" ")
				.append(request.getRequestURI()).append(" ").append(StringUtils.repeat("*", bannerLength));

		return logMessage.toString();
	}

	private String getRequestParametersLogMessage(HttpServletRequest request) {
		StringBuilder logMessage = new StringBuilder();

		@SuppressWarnings("unchecked")
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (!parameterMap.isEmpty()) {
			logMessage.append("Received request params (").append(request.hashCode()).append("):").append(newline);
			for (String paramName : parameterMap.keySet()) {
				logMessage.append("  \"").append(paramName).append("\" = [");
				String[] parameterValues = parameterMap.get(paramName);
				for (String parameterValue : parameterValues) {
					logMessage.append("\"").append(parameterValue).append("\",");
				}
				logMessage.deleteCharAt(logMessage.length() - 1);
				logMessage.append("]").append(newline);
			}
			logMessage.deleteCharAt(logMessage.length() - newline.length());
		} else {
			logMessage.append("No request parameters received (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();
	}

	private String getRequestHeadersLogMessage(HttpServletRequest request) {

		StringBuilder logMessage = new StringBuilder();

		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames.hasMoreElements()) {
			logMessage.append("Received request headers (").append(request.hashCode()).append("):").append(newline);

			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				logMessage.append("  \"").append(headerName).append("\" = [");
				for (@SuppressWarnings("unchecked")
				Enumeration<String> headerValues = request.getHeaders(headerName); headerValues.hasMoreElements();) {
					String headerValue = headerValues.nextElement();
					logMessage.append("\"").append(headerValue).append("\",");
				}
				logMessage.deleteCharAt(logMessage.length() - 1);
				logMessage.append("]").append(newline);
			}
			logMessage.deleteCharAt(logMessage.length() - newline.length());
		} else {
			logMessage.append("No request headers received (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();

	}

	private String getRequestCookiesLogMessage(HttpServletRequest request) {

		StringBuilder logMessage = new StringBuilder();

		Cookie[] cookies = request.getCookies();
		if ((null != cookies) && (cookies.length > 0)) {
			logMessage.append("Received cookies (").append(request.hashCode()).append("):").append(newline);
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();
				logMessage.append("  \"").append(cookieName).append("\" = \"").append(cookieValue).append("\"")
						.append(newline);
			}
		} else {
			logMessage.append("No cookies received (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();
	}

}
