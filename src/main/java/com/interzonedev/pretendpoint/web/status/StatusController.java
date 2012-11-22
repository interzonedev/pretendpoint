package com.interzonedev.pretendpoint.web.status;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interzonedev.pretendpoint.web.PretendPointController;

@Controller
@RequestMapping(value = "/status")
public class StatusController extends PretendPointController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getStatus() {
		log.debug("getStatus: Start");

		log.debug("getStatus: End");

		return "{}";
	}

}
