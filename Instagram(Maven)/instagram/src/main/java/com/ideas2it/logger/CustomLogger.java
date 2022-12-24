package com.ideas2it.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * use to log the information.
 * 
 * @version 1.0 13 Oct 2022
 * @author Yogeshwar S
 */
public class CustomLogger {
    private Logger logger;

    public CustomLogger(Class<?> className) {
        logger = LogManager.getLogger(className);
    }

    /**
     * logs the information message.
     *
     * @param logging message of function
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * logs the warning message.
     *
     * @param warn message of function
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * logs the error message.
     * 
     * @param error message of function 
     */
    public void error(String message) {
        logger.error(message);
    }

    /**
     * logs the fatal message.
     * 
     * @param fatal message of function
     */
    public void fatal(String message) {
        logger.fatal(message);
    }
}