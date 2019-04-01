package ru.job4j.jdbc;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Valeriy Gyrievskikh
 * @since 30.03.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    /**
     * Подключение к указанной базе данных.
     */
    private Connection connection;

    /**
     * Метод создает подключение к базе данных с указанными настройками.
     *
     * @return Результат подключения (true - подключение создано, false - не создано).
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null && initTables();
    }

    /**
     * Метод, реализаущий добавление заявки в хранилище.
     *
     * @param item Новая заявка.
     */
    @Override
    public Item add(Item item) {
        Item newItem = null;
        String sqlText = "INSERT INTO requests (name, request_id, user_id, category_id, status_id)"
                + "VALUES(?, ?, 1, 1, 1)";
        try (PreparedStatement statement = this.connection.prepareStatement(sqlText)) {
            statement.setString(1, item.getName());
            statement.setString(2, Long.toString(item.getNumber()));
            statement.executeUpdate();
            newItem = item;
            newItem.setId(Long.toString(item.getNumber()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newItem;
    }

    /**
     * Метод, реализующий замену заявки в хранилище.
     *
     * @param id   Идентификатор заявки.
     * @param item Заявка.
     */
    @Override
    public void replace(String id, Item item) {
        String sqlText = "UPDATE requests SET name = ? where request_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sqlText)) {
            statement.setString(1, item.getName());
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, реализующий удаление заявки.
     *
     * @param id Идентификатор заявки.
     */
    @Override
    public void delete(String id) {
        String sqlText = "DELETE FROM requests WHERE request_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sqlText)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, возвращающий список заявок.
     *
     * @return Список заявок без пустых значений или null.
     */
    @Override
    public List<Item> findAll() {
        String sqlText = "SELECT * FROM requests";
        List<Item> requests = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlText);
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), "", rs.getInt("id"));
                item.setId(rs.getString("request_id"));
                requests.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests.size() == 0 ? null : requests;
    }

    /**
     * Метод, возвращающий список заявок по названию.
     *
     * @param key Название заявки.
     * @return Список заявок с одинаковым именем или null.
     */
    @Override
    public List<Item> findByName(String key) {
        String sqlText = "SELECT * FROM requests WHERE name = ?";
        List<Item> requests = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sqlText)) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), "", rs.getInt("id"));
                item.setId(rs.getString("request_id"));
                requests.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests.size() == 0 ? null : requests;
    }

    /**
     * Метод, возвращающий заявку по идентификатору.
     *
     * @param id Идентификатор заявки.
     * @return Найденная заявка или null.
     */
    @Override
    public Item findById(String id) {
        String sqlText = "SELECT * FROM requests WHERE request_id = ?";
        Item item = null;
        try (PreparedStatement statement = this.connection.prepareStatement(sqlText)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                item = new Item(rs.getString("name"), "", rs.getInt("id"));
                item.setId(rs.getString("request_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод закрывает этот и исходные ресурсы.
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Метод запускает проверку таблиц в базе данных, с которой установлено соединение.
     *
     * @return Результат проверки (false - если в процессе проверки возникали ошибки, иначе true).
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
     * Метод проверяет таблицы в базе и при необходимости создает новые таблицы с данными по умолчанию.
     *
     * @param statement Текущий контейнер для выполнения SQL-запросов.
     * @throws SQLException Возможные ошибки доступа к базе данных.
     */
    private void verifyTables(Statement statement) throws SQLException {
        String queryRoles = "CREATE TABLE IF NOT EXISTS roles(id int primary key,name varchar (100));";
        statement.executeUpdate(queryRoles);
        ResultSet rs = statement.executeQuery("SELECT * FROM roles;");
        if (!rs.next()) {
            statement.executeUpdate("INSERT INTO roles (id, name) VALUES(1, 'DefaultRole');");
        }
        String queryRules = "CREATE TABLE IF NOT EXISTS rules(id int primary key,name varchar (100));";
        statement.executeUpdate(queryRules);
        rs = statement.executeQuery("SELECT * FROM rules;");
        if (!rs.next()) {
            statement.executeUpdate("INSERT INTO rules (id, name) VALUES(1, 'DefaultRule');");
        }
        String queryRulesRoles = "CREATE TABLE IF NOT EXISTS rules_to_roles"
                + "(id int primary key,role_id int REFERENCES roles(id),rigth_id int REFERENCES rules(id));";
        statement.executeUpdate(queryRulesRoles);
        rs = statement.executeQuery("SELECT * FROM rules_to_roles;");
        if (!rs.next()) {
            statement.executeUpdate("INSERT INTO rules_to_roles (id, role_id, rigth_id) VALUES(1, 1, 1);");
        }
        String queryUsers = "CREATE TABLE IF NOT EXISTS users (id int primary key, name varchar (100)"
                + ", role_id int REFERENCES roles(id));";
        statement.executeUpdate(queryUsers);
        rs = statement.executeQuery("SELECT * FROM users;");
        if (!rs.next()) {
            statement.executeUpdate("INSERT INTO users (id, name, role_id) VALUES(1, 'DefaultUser', 1);");
        }
        String queryCatogories = "CREATE TABLE IF NOT EXISTS category(id int primary key,name varchar (100));";
        statement.executeUpdate(queryCatogories);
        rs = statement.executeQuery("SELECT * FROM category;");
        if (!rs.next()) {
            statement.executeUpdate("INSERT INTO category (id, name) VALUES(1, 'DefaultCategory');");
        }
        String queryStatuses = "CREATE TABLE IF NOT EXISTS status(id int primary key, name varchar (10));";
        statement.executeUpdate(queryStatuses);
        rs = statement.executeQuery("SELECT * FROM status;");
        if (!rs.next()) {
            statement.executeUpdate("INSERT INTO status (id, name) VALUES(1, 'Opened');");
            statement.executeUpdate("INSERT INTO status (id, name) VALUES(2, 'Handles');");
            statement.executeUpdate("INSERT INTO status (id, name) VALUES(3, 'Completed');");
            statement.executeUpdate("INSERT INTO status (id, name) VALUES(4, 'Returned');");
            statement.executeUpdate("INSERT INTO status (id, name) VALUES(5, 'Closed');");
        }
        String queryRequests = "CREATE TABLE IF NOT EXISTS requests"
                + "(id SERIAL primary key, name varchar (100), request_id varchar (100),"
                + "user_id int REFERENCES users(id), status_id int REFERENCES status(id),"
                + "category_id int REFERENCES category(id));";
        statement.executeUpdate(queryRequests);
        String queryComments = "CREATE TABLE IF NOT EXISTS comment(id int primary key,name varchar (100)"
                + ",request_id int REFERENCES requests(id));";
        statement.executeUpdate(queryComments);
        String queryFiles = "CREATE TABLE IF NOT EXISTS files(id int primary key, name varchar (100)"
                + ",request_id int REFERENCES requests(id));";
        statement.executeUpdate(queryFiles);
    }
}
