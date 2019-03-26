package ru.job4j.JDBC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2019
 */
public class SQLStorage {
    /**
     * Логгер.
     */
    private static final Logger LOG = LogManager.getLogger(SQLStorage.class.getName());

    /**
     * Метод возвращает текущий логгер.
     *
     * @return Логгер.
     */
    public Logger getLog() {
        return LOG;
    }
}
