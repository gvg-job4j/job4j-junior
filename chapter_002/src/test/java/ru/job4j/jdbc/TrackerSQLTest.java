package ru.job4j.jdbc;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 30.03.2019
 */
public class TrackerSQLTest {

    private TrackerSQL tracker;

    @Before
    public void initTracker() {
        tracker = new TrackerSQL();
        tracker.init();
        List<Item> currentRequests = tracker.findAll();
        if (currentRequests != null) {
            for (Item item : currentRequests) {
                tracker.delete(item.getId());
            }
        }
    }

    private Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() {
        TrackerSQL tracker = new TrackerSQL();
        assertThat(tracker.init(), is(true));
    }

    @Test
    public void checkTables() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        assertThat(tracker.initTables(), is(true));
    }

    @Test
    public void createItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "testDescription", 123L));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDescription", 123L);
            tracker.add(item);
            assertThat(tracker.findAll().get(0).getId(), Is.is(item.getId()));
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item previous = new Item("test1", "testDescription", 123L);
            tracker.add(previous);
            previous.setId(Long.toString(previous.getNumber()));
            Item next = new Item("test2", "testDescription2", 1234L);
            next.setId(Long.toString(next.getNumber()));
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(previous.getId()).getName(), Is.is("test2"));
        }
    }

    @Test
    public void whenDeleteItemThenReturnNull() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item newItem1 = new Item("test1", "testDescription", 123L);
            Item newItem2 = new Item("test2", "testDescription", 1234L);
            Item newItem3 = new Item("test3", "testDescription", 12345L);
            newItem1.setId(Long.toString(newItem1.getNumber()));
            newItem2.setId(Long.toString(newItem1.getNumber()));
            newItem3.setId(Long.toString(newItem1.getNumber()));
            tracker.add(newItem1);
            tracker.add(newItem2);
            tracker.add(newItem3);
            tracker.delete(newItem1.getId());
            assertNull(tracker.findById(newItem1.getId()));
        }
    }

    @Test
    public void whenFindAllThenReturnArray() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item newItem = new Item("test1", "testDescription", 123L);
            tracker.add(newItem);
            assertThat(tracker.findAll().size(), Is.is(1));
        }
    }

    @Test
    public void whenFindByNameThenReturnArrayLength() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item newItem1 = new Item("test1", "testDescription", 123L);
            Item newItem2 = new Item("test1", "testDescription", 1234L);
            Item newItem3 = new Item("test3", "testDescription", 123L);
            tracker.add(newItem1);
            tracker.add(newItem2);
            tracker.add(newItem3);
            assertThat(tracker.findByName("test1").size(), Is.is(2));
        }
    }

    @Test
    public void whenFindByIdThenReturnItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item newItem = new Item("test1", "testDescription", 123L);
            tracker.add(newItem);
            assertThat(tracker.findById(newItem.getId()).getId(), Is.is(newItem.getId()));
        }
    }
}
