package ru.job4j.jdbc.parsersqlru;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Valeriy Gyrievskikh
 * @since 04.09.2019
 */
public class DatabaseManager implements AutoCloseable {

    /**
     * Подключение к базе данных.
     */
    private Connection connection;

    /**
     * Идентификатор первой проверки.
     */
    private boolean isFirst = true;

    /**
     * Конструктор по умолчанию.
     */
    public DatabaseManager() {

    }

    /**
     * Конструктор, устанавливает подключение к базе данных.
     *
     * @param connection Текущее подключение.
     */
    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод возвращает текущее подключение к базе данных.
     *
     * @return Текущее подключение.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Метод возвращает идентификатор первой проверки.
     *
     * @return Идентификатор первой проверки.
     */
    public boolean isFirst() {
        return isFirst;
    }

    /**
     * Метод устанавливает подключение к базе данных по данным файла настройки.
     *
     * @return Результат подключения.
     */
    public boolean connect() {
        Properties properties = ParserSheduler.getProperties();
        if (properties.size() == 0) {
            new ParserSheduler().initScheduler("app.properties");
            properties = ParserSheduler.getProperties();
        }
        try {
            Class.forName(properties.getProperty("jdbc.driver"));
            this.connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return this.connection != null && initTables();
    }

    /**
     * Метод выполняет проверку таблиц базы данных.
     *
     * @return Результат проверки.
     */
    public boolean initTables() {
        boolean verify = false;
        if (this.connection != null) {
            try (Statement statement = this.connection.createStatement()) {
                verifyTables(statement);
                verify = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return verify;
    }

    /**
     * Метод выполняет создание таблиц базы данных, если их нет,
     * и устанавливает идентификатор первого подключения.
     *
     * @param statement Созданное утверждение.
     * @throws SQLException Возможное исключение.
     */
    private void verifyTables(Statement statement) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS vacancy(id serial primary key unique,"
                + "name varchar (100),"
                + "text varchar(1000),"
                + "link varchar(100));";
        statement.executeUpdate(query);
        query = "SELECT COUNT(id) from vacancy";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            isFirst = (rs.getInt("count") == 0);
        }
    }

    /**
     * Метод выполняет проверку на дубликаты.
     *
     * @param list Список, содержаний полученные данные.
     * @return Список, содержащий данные без дубликатов.
     */
    public List<String[]> checkDoublicates(List<String[]> list) {
        List<String[]> noDublList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        if (this.connection != null) {
            try (Statement statement = this.connection.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM vacancy;");
                while (rs.next()) {
                    names.add(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (String[] row : list) {
                if (names.contains(row[1])) {
                    continue;
                }
                noDublList.add(row);
            }
        }
        return noDublList;
    }

    /**
     * Метод выполняет запись в базу данных.
     *
     * @param list Список данных для записи.
     * @return Результат записи.
     */
    public boolean writeAll(List<String[]> list) {
        boolean write = false;
        if (this.connection != null) {
            try (PreparedStatement prepStmt = this.connection.prepareStatement(
                    "INSERT INTO vacancy (field) VALUES(?)")) {
                this.connection.setAutoCommit(false);
                for (String[] row : list) {
                    prepStmt.setString(1, row[1]);
                    prepStmt.setString(2, row[2]);
                    prepStmt.setString(3, row[3]);
                    prepStmt.addBatch();
                }
                prepStmt.executeBatch();
                this.connection.commit();
                write = true;
            } catch (SQLException e) {
                try {
                    this.connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return write;
    }

    /**
     * Метод закрывает этот и исходные ресурсы.
     *
     * @throws Exception Возможное исключение.
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
