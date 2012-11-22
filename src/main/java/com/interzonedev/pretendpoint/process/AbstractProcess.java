package com.interzonedev.pretendpoint.process;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.interzonedev.herokusupport.client.DefaultHerokuSupportClient;
import com.interzonedev.herokusupport.client.HerokuSupportClient;

public abstract class AbstractProcess {

	protected Log log = LogFactory.getLog(getClass());

	protected HerokuSupportClient herokuSupportClient = new DefaultHerokuSupportClient();

	abstract void process(String[] args) throws Exception;

}
