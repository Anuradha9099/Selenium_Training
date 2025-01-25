package com.log4jtests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;


public class LoggingTest {

    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    @Test
    public void testLogMethod() {

        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("This is an INFO message");
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR message");
        logger.fatal("This is a FATAL message");
    }
}
