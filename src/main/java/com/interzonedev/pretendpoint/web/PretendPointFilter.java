package com.interzonedev.pretendpoint.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.interzonedev.pretendpoint.web.PretendPointRequestListener.RESPONSE_ATTRIBUTE_NAME;


public class PretendPointFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(PretendPointFilter.class);

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
