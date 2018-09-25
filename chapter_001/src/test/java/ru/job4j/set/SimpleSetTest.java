package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Valeriy Gyrievskikh
 * @since 18.09.2018
 */
public class SimpleSetTest {

    @Test
    public void whenTakeNextThenFirstElement() {
        SimpleSet<String> list = new SimpleSet<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("1"));
    }

    @Test
    public void whenTakeNextAfterChangeSizeThenException() {
        SimpleSet<String> list = new SimpleSet<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("1"));
        list.add("6");
        try {
            iterator.hasNext();
            fail("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenNewIteratorAfterChangeSize() {
        SimpleSet<String> list = new SimpleSet<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        list.add("6");
        iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("1"));
    }
}
