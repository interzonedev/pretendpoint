package com.interzonedev.pretendpoint.web;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class PretendPointRequestListener implements ServletRequestListener {

	public static final String NEWLINE = System.getProperty("line.separator", "\n");

	public static final String RESPONSE_ATTRIBUTE_NAME = "httpServletResponse";

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
		HttpServletResponse response = (HttpServletResponse) request.getAttribute(RESPONSE_ATTRIBUTE_NAME);

		log.debug(getRequestBoundaryLogMessage(request, 7, "Ending"));

		log.debug(getResponsePropertiesLogMessage(request, response));

		log.debug(getResponseHeadersLogMessage(request, response));

		log.debug(getRequestBoundaryLogMessage(request, 14, "Sending"));

	}

	private String getRequestBoundaryLogMessage(HttpServletRequest request, int bannerLength, String title) {
		StringBuilder logMessage = new StringBuilder();

		logMessage.append(StringUtils.repeat("*", bannerLength)).append(" ").append(title).append(" (")
				.append(request.hashCode()).append("): ").append(request.getMethod()).append(" ")
				.append(request.getScheme()).append(" ").append(request.getRequestURI()).append(" ")
				.append(StringUtils.repeat("*", bannerLength));

		return logMessage.toString();
	}

	private String getRequestParametersLogMessage(HttpServletRequest request) {
		StringBuilder logMessage = new StringBuilder();

		Map<String, String[]> parameterMap = request.getParameterMap();
		if (!parameterMap.isEmpty()) {
			logMessage.append("Received request params (").append(request.hashCode()).append("):").append(NEWLINE);
			for (String paramName : parameterMap.keySet()) {
				logMessage.append("  \"").append(paramName).append("\" = [");
				String[] parameterValues = parameterMap.get(paramName);
				for (String parameterValue : parameterValues) {
					logMessage.append("\"").append(parameterValue).append("\",");
				}
				logMessage.deleteCharAt(logMessage.length() - 1);
				logMessage.append("]").append(NEWLINE);
			}
			logMessage.deleteCharAt(logMessage.length() - NEWLINE.length());
		} else {
			logMessage.append("No request parameters received (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();
	}

	private String getRequestHeadersLogMessage(HttpServletRequest request) {

		StringBuilder logMessage = new StringBuilder();

		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames.hasMoreElements()) {
			logMessage.append("Received request headers (").append(request.hashCode()).append("):").append(NEWLINE);

			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				logMessage.append("  \"").append(headerName).append("\" = [");
				for (Enumeration<String> headerValues = request.getHeaders(headerName); headerValues.hasMoreElements();) {
					String headerValue = headerValues.nextElement();
					logMessage.append("\"").append(headerValue).append("\",");
				}
				logMessage.deleteCharAt(logMessage.length() - 1);
				logMessage.append("]").append(NEWLINE);
			}
			logMessage.deleteCharAt(logMessage.length() - NEWLINE.length());
		} else {
			logMessage.append("No request headers received (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();

	}

	private String getRequestCookiesLogMessage(HttpServletRequest request) {

		StringBuilder logMessage = new StringBuilder();

		Cookie[] cookies = request.getCookies();
		if ((null != cookies) && (cookies.length > 0)) {
			logMessage.append("Received cookies (").append(request.hashCode()).append("):").append(NEWLINE);
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();
				logMessage.append("  \"").append(cookieName).append("\" = \"").append(cookieValue).append("\"")
						.append(NEWLINE);
			}
		} else {
			logMessage.append("No cookies received (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();
	}

	private String getResponsePropertiesLogMessage(HttpServletRequest request, HttpServletResponse response) {

		StringBuilder logMessage = new StringBuilder();

		logMessage.append("Outgoing response properties (").append(request.hashCode()).append("):").append(NEWLINE);
		logMessage.append("  status = " + response.getStatus()).append(NEWLINE);
		logMessage.append("  content type = \"" + response.getContentType()).append("\"").append(NEWLINE);
		logMessage.append("  locale = " + response.getLocale());

		return logMessage.toString();

	}

	private String getResponseHeadersLogMessage(HttpServletRequest request, HttpServletResponse response) {

		StringBuilder logMessage = new StringBuilder();

		Collection<String> headerNames = response.getHeaderNames();
		if (!headerNames.isEmpty()) {
			logMessage.append("Set response headers (").append(request.hashCode()).append("):").append(NEWLINE);

			for (String headerName : headerNames) {
				Collection<String> headerValues = response.getHeaders(headerName);
				logMessage.append("    \"").append(headerName).append("\" = [");
				for (String headerValue : headerValues) {
					logMessage.append("\"").append(headerValue).append("\",");
				}
				logMessage.deleteCharAt(logMessage.length() - 1);
				logMessage.append("]").append(NEWLINE);
			}
			logMessage.deleteCharAt(logMessage.length() - NEWLINE.length());
		} else {
			logMessage.append("No response headers set (").append(request.hashCode()).append(")");
		}

		return logMessage.toString();

	}

}
