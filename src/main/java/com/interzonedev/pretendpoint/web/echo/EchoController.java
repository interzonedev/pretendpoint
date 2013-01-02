package com.interzonedev.pretendpoint.web.echo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interzonedev.pretendpoint.web.PretendPointController;

@Controller
@RequestMapping(value = "/echo")
public class EchoController extends PretendPointController {

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
			RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE })
	public ResponseEntity<String> echo(HttpServletRequest request) throws JsonGenerationException,
			JsonMappingException, IOException {

		log.debug("echo - Start");

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

		EchoResponse echoResponse = new EchoResponse(url, method, parameters, headers);

		String responseBody = (new ObjectMapper()).writeValueAsString(echoResponse);

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseBody, HttpStatus.OK);

		log.debug("echo - End");

		return responseEntity;

	}

}
