package ru.job4j.jdbc.datamanipulate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 31.03.2019
 */
public class StoreSQL implements AutoCloseable {
    /**
     * Данные для подключения.
     */
    private final Config config;
    /**
     * Подключение к БД.
     */
    private Connection connect;

    /**
     * Конструктор, получает данные для подключения.
     *
     * @param config Данные для подключения.
     */
    public StoreSQL(Config config) {
        this.config = config;
        config.init();
        try (Connection conn = DriverManager.getConnection(config.get("url"))) {
            this.connect = conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает установленное подключение к БД.
     *
     * @return Подключение.
     */
    public Connection getConnect() {
        return connect;
    }

    /**
     * Метод формирует таблицу в БД и заполняет ее.
     *
     * @param size Количество записей в таблице.
     */
    public void generate(int size) {
        try (Connection conn = DriverManager.getConnection(config.get("url"))) {
            if (conn != null) {
                try (Statement statement = conn.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS entry(field integer);");
                    statement.executeUpdate("DELETE FROM entry;");
                    conn.setAutoCommit(false);
                    for (int i = 1; i <= size; i++) {
                        statement.executeUpdate("INSERT INTO entry (field) VALUES(" + i + ");");
                    }
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод считывает данные из таблицы.
     *
     * @return Список данных.
     */
    public List<Entry> load() {
        List<Entry> values = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(config.get("url"))) {
            if (conn != null) {
                try (Statement statement = conn.createStatement()) {
                    ResultSet rs = statement.executeQuery("SELECT * FROM entry;");
                    while (rs.next()) {
                        values.add(new Entry(rs.getInt("field")));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * Метод закрывает открытые подключения.
     *
     * @throws Exception Возможные ошибки.
     */
    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
