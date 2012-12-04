package com.interzonedev.pretendpoint.web.login;

import java.io.IOException;
import java.util.UUID;

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
@RequestMapping(value = "/login")
public class LoginController extends PretendPointController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> login(LoginForm loginForm) throws JsonGenerationException, JsonMappingException,
			IOException {

		log.debug("login - Start");

		String authToken = null;
		HttpStatus httpStatus = HttpStatus.OK;

		if ("fail1234".equals(loginForm.getPassword())) {
			httpStatus = HttpStatus.FORBIDDEN;
		} else {
			authToken = UUID.randomUUID().toString();
		}

		LoginResponse loginResponse = new LoginResponse(authToken);

		String responseBody = (new ObjectMapper()).writeValueAsString(loginResponse);

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseBody, httpStatus);

		log.debug("login - End");

		return responseEntity;

	}

}
