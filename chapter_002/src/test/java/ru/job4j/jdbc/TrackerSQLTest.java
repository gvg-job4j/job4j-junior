package ru.job4j.jdbc;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;

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

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void checkTables() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        assertThat(sql.initTables(), is(true));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0).getId(), Is.is(item.getId()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        previous.setId(Long.toString(previous.getNumber()));
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(Long.toString(next.getNumber()));
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), Is.is("test2"));
    }

    @Test
    public void whenDeleteItemThenReturnNull() {
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

    @Test
    public void whenFindAllThenReturnArray() {
        Item newItem = new Item("test1", "testDescription", 123L);
        tracker.add(newItem);
        assertThat(tracker.findAll().size(), Is.is(1));
    }

    @Test
    public void whenFindByNameThenReturnArrayLength() {
        Item newItem1 = new Item("test1", "testDescription", 123L);
        Item newItem2 = new Item("test1", "testDescription", 1234L);
        Item newItem3 = new Item("test3", "testDescription", 123L);
        tracker.add(newItem1);
        tracker.add(newItem2);
        tracker.add(newItem3);
        assertThat(tracker.findByName("test1").size(), Is.is(2));
    }

    @Test
    public void whenFindByIdThenReturnItem() {
        Item newItem = new Item("test1", "testDescription", 123L);
        tracker.add(newItem);
        assertThat(tracker.findById(newItem.getId()).getId(), Is.is(newItem.getId()));
    }
}
