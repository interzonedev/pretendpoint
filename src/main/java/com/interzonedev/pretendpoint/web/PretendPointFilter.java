package com.interzonedev.pretendpoint.web;

import static com.interzonedev.pretendpoint.web.PretendPointRequestListener.NEWLINE;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class PretendPointFilter implements Filter {

	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		chain.doFilter(request, response);

		log.debug(getResponsePropertiesLogMessage((HttpServletRequest) request, (HttpServletResponse) response));
	}

	@Override
	public void destroy() {
	}

	private String getResponsePropertiesLogMessage(HttpServletRequest request, HttpServletResponse response) {

		StringBuilder logMessage = new StringBuilder();

		logMessage.append("Outgoing response properties (").append(request.hashCode()).append("):").append(NEWLINE);
		logMessage.append("  status = " + response.getStatus()).append(NEWLINE);
		logMessage.append("  content type = \"" + response.getContentType()).append("\"").append(NEWLINE);
		logMessage.append("  locale = " + response.getLocale()).append(NEWLINE);

		return logMessage.toString();

	}

	@SuppressWarnings("unused")
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
