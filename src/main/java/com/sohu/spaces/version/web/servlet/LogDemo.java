package com.sohu.spaces.version.web.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogDemo {

    private static Logger logger = LoggerFactory.getLogger(LogDemo.class);;
    public static void main(String[] args) {
        logger.warn("warn xsfsdf");
        logger.info("info xinxi");
        java.util.logging.Logger logDemo = java.util.logging.Logger.getLogger("LogDemo");
        logDemo.info("jul info");
        Log log = LogFactory.getLog(LogDemo.class);
        log.info("jcl info");
    }
}
