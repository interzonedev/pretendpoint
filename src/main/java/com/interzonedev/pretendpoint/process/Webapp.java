package com.interzonedev.pretendpoint.process;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.interzonedev.herokusupport.client.DefaultHerokuSupportClient;
import com.interzonedev.herokusupport.client.HerokuSupportClient;
import com.interzonedev.herokusupport.webserver.WebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerType;

public class Webapp {

    private static final Logger log = (Logger) LoggerFactory.getLogger(Webapp.class);

    public static void main(String[] args) throws Exception {

        log.debug("main: Launching Jetty server");

        HerokuSupportClient herokuSupportClient = new DefaultHerokuSupportClient();

        WebServerParams webServerParams = new WebServerParams(5001);

        herokuSupportClient.startWebServer(WebServerType.JETTY, webServerParams, null, null, null);

    }

}
