package com.interzonedev.pretendpoint.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

public abstract class PretendPointController {

    private static final Logger log = LoggerFactory.getLogger(PretendPointController.class);

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
