package ru.job4j.jdbc.parsersqlru;

import org.junit.Test;
import ru.job4j.jdbc.ConnectionRollback;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 04.09.2019
 */
public class DatabaseManagerTest {

//    private Connection init() {
//        try (InputStream in = DatabaseManager.class.getClassLoader().getResourceAsStream("app.properties")) {
//            Properties config = new Properties();
//            config.load(in);
//            Class.forName(config.getProperty("jdbc.driver"));
//            return DriverManager.getConnection(
//                    config.getProperty("jdbc.url"),
//                    config.getProperty("jdbc.username"),
//                    config.getProperty("jdbc.password")
//            );
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    @Test
//    public void whenConnectThenTrue() {
//        assertTrue(new DatabaseManager().connect());
//    }
//
//    @Test
//    public void whenAddVacancyThenGetVacancyName() throws Exception {
//        String name = "";
//        try (DatabaseManager manager = new DatabaseManager(ConnectionRollback.create(this.init()))) {
//            Connection connection = manager.getConnection();
//            String sqlText = "INSERT INTO vacancy (name, text, link)"
//                    + "VALUES(?, ?, ?)";
//            try (PreparedStatement statement = connection.prepareStatement(sqlText)) {
//                statement.setString(1, "Name");
//                statement.setString(2, "Text");
//                statement.setString(3, "Link");
//                statement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try (Statement statement = connection.createStatement()) {
//                ResultSet rs = statement.executeQuery("SELECT * FROM vacancy;");
//                if (rs.next()) {
//                    name = rs.getString("name");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        assertThat(name, is("Name"));
//    }
}