package com.interzonedev.pretendpoint.web.status;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interzonedev.pretendpoint.web.PretendPointController;

@Controller
@RequestMapping(value = "/status")
public class StatusController extends PretendPointController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public StatusResponse getStatus() {

		log.debug("getStatus - Start");

		StatusResponse statusResponse = new StatusResponse();

		log.debug("getStatus - End");

		return statusResponse;

	}

}
