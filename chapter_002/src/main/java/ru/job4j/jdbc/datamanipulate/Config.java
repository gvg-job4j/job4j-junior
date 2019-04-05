package ru.job4j.jdbc.datamanipulate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Valeriy Gyrievskikh
 * @since 31.03.2019
 */
public class Config {
    /**
     * Параметры подключения.
     */
    private final Properties values = new Properties();

    /**
     * Метод выполняет инициализацию параметров подключения к БД.
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("sqlite.properties")) {
            values.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает значение свойства по имени свойства.
     *
     * @param key Имя свойства.
     * @return Значение свойства.
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }

}
