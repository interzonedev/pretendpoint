package com.interzonedev.pretendpoint.web;

import static com.interzonedev.pretendpoint.web.PretendPointRequestListener.RESPONSE_ATTRIBUTE_NAME;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

		log.debug("doFilter: Setting outgoing response in request attributes");
		request.setAttribute(RESPONSE_ATTRIBUTE_NAME, (HttpServletResponse) response);
	}

	@Override
	public void destroy() {
	}

}
