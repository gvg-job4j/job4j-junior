package ru.job4j.JDBC;

import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2019
 */
public class LogTest {

    @Test
    public void teslLogger(){
        SQLStorage storage = new SQLStorage();
        Logger log = storage.getLog();
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
        assertTrue(true);
    }
}
