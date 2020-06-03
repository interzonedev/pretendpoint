package com.interzonedev.pretendpoint.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/")
public class DefaultViewController extends PretendPointController {

    private static final Logger log = LoggerFactory.getLogger(DefaultViewController.class);

    @RequestMapping(method = RequestMethod.GET)
    public RedirectView redirectToEcho() {
        try {
            log.debug("redirectToEcho: Start");
            return new RedirectView("/echo");
        } finally {
            log.debug("redirectToEcho: End");
        }
    }

}
