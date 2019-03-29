package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 19.06.2018.
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemThenReturnNull() {
        Tracker tracker = new Tracker();
        Item newItem1 = new Item("test1", "testDescription", 123L);
        Item newItem2 = new Item("test2", "testDescription", 1234L);
        Item newItem3 = new Item("test3", "testDescription", 12345L);
        tracker.add(newItem1);
        tracker.add(newItem2);
        tracker.add(newItem3);
        tracker.delete(newItem1.getId());
        Item expect = null;
        assertThat(tracker.findById(newItem1.getId()), is(expect));
    }

    @Test
    public void whenFindAllThenReturnArrayLength() {
        Tracker tracker = new Tracker();
        Item newItem = new Item("test1", "testDescription", 123L);
        tracker.add(newItem);
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void whenFindByNameThenReturnArrayLength() {
        Tracker tracker = new Tracker();
        Item newItem1 = new Item("test1", "testDescription", 123L);
        Item newItem2 = new Item("test1", "testDescription", 1234L);
        Item newItem3 = new Item("test3", "testDescription", 123L);
        tracker.add(newItem1);
        tracker.add(newItem2);
        tracker.add(newItem3);
        assertThat(tracker.findByName("test1").size(), is(2));
    }

    @Test
    public void whenFindByIdThenReturnItem() {
        Tracker tracker = new Tracker();
        Item newItem = new Item("test1", "testDescription", 123L);
        tracker.add(newItem);
        assertThat(tracker.findById(newItem.getId()), is(newItem));
    }

}
