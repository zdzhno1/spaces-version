package com.sohu.spaces.version.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogDemo {

    private static Logger logger = LoggerFactory.getLogger(LogDemo.class);;
    public static void main(String[] args) {
        for (int i = 0; i < 500000 ; i++) {
            logger.warn("warn xsfsdf");
            logger.info("info xinxi");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
