package com.interzonedev.pretendpoint.web.echo;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interzonedev.pretendpoint.web.PretendPointController;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/echo")
public class EchoController extends PretendPointController {

    private static final Logger log = LoggerFactory.getLogger(EchoController.class);

    @ResponseBody
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
            RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE })
    public ResponseEntity<String> echo(EchoForm echoForm, HttpServletRequest request) throws JsonGenerationException,
            JsonMappingException, IOException, URISyntaxException {

        log.debug("echo: Start");

        HttpStatus httpStatus = HttpStatus.OK;
        try {
            httpStatus = HttpStatus.valueOf(echoForm.getHttpStatusValue());
        } catch (IllegalArgumentException iae) {
            // Ignore
        }

        String body = getRequestBody(request);

        String url = request.getRequestURL().toString();

        String method = request.getMethod();

        Map<String, List<String>> parameters = new HashMap<String, List<String>>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()) {
            for (String paramName : parameterMap.keySet()) {
                List<String> parameterValues = Arrays.asList(parameterMap.get(paramName));
                parameters.put(paramName, parameterValues);
            }
        }

        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames.hasMoreElements()) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                List<String> headerValues = new ArrayList<String>();
                for (Enumeration<String> headerValuesEnum = request.getHeaders(headerName); headerValuesEnum
                        .hasMoreElements();) {
                    String headerValue = headerValuesEnum.nextElement();
                    headerValues.add(headerValue);
                }
                headers.put(headerName, headerValues);
            }
        }

        EchoResponse echoResponse = new EchoResponse(url, method, parameters, headers, body);

        String responseBody = (new ObjectMapper()).writeValueAsString(echoResponse);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.remove("Content-Type");
        responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        if (StringUtils.isNotBlank(echoForm.getHttpLocation())) {
            responseHeaders.setLocation(new URI(echoForm.getHttpLocation().trim()));
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseBody, responseHeaders, httpStatus);

        log.debug("echo: responseEntity = " + responseEntity);

        try {
            long sleepMillis = Long.parseLong(echoForm.getSleepMillis());
            Thread.sleep(sleepMillis);
        } catch (Exception e) {
            // Do nothing.
        }

        log.debug("echo: End");

        return responseEntity;

    }

    public String getRequestBody(HttpServletRequest request) throws IOException {
        return IOUtils.toString(request.getInputStream(), "UTF-8");
    }
    
}
