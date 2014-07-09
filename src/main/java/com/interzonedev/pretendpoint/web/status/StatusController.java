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
    public StatusResponse getStatus(StatusForm status) {

        log.debug("getStatus - Start");

        logEnvironment();
        logSystem();

        if (status.isError()) {
            throw new RuntimeException("There was an error");
        }

        StatusResponse statusResponse = new StatusResponse();

        log.debug("getStatus - End");

        return statusResponse;

    }

    private void logEnvironment() {
        log.debug("***** Starting Output of Environment Variables *****");
        System.getenv().forEach((key, value) -> {
            log.debug(key + " = " + value);
        });
        log.debug("***** Ending Output of Environment Variables *****");
    }

    private void logSystem() {
        log.debug("***** Starting Output of System Variables *****");
        System.getProperties().forEach((key, value) -> {
            log.debug(key + " = " + value);
        });
        log.debug("***** Ending Output of System Variables *****");
    }

}
