package com.interzonedev.pretendpoint.web.login;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interzonedev.pretendpoint.web.PretendPointController;
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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends PretendPointController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

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

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.remove("Content-Type");
        responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseBody, responseHeaders, httpStatus);

        log.debug("login - End");

        return responseEntity;

    }

}
