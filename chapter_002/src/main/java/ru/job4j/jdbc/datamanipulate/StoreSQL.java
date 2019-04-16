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
        try {
            this.connect = DriverManager.getConnection(config.get("url"));
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
        if (this.connect != null) {
            try (Statement statement = this.connect.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS entry(field integer);");
                statement.executeUpdate("DELETE FROM entry;");
                try (PreparedStatement prepStmt = this.connect.prepareStatement(
                        "INSERT INTO entry (field) VALUES(?)")) {
                    this.connect.setAutoCommit(false);
                    for (int i = 1; i <= size; i++) {
                        prepStmt.setInt(1, i);
                        prepStmt.addBatch();
                    }
                    prepStmt.executeBatch();
                    this.connect.commit();
                } catch (SQLException e) {
                    this.connect.rollback();
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод считывает данные из таблицы.
     *
     * @return Список данных.
     */
    public List<Entry> load() {
        List<Entry> values = new ArrayList<>();
        if (this.connect != null) {
            try (Statement statement = this.connect.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM entry;");
                while (rs.next()) {
                    values.add(new Entry(rs.getInt("field")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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