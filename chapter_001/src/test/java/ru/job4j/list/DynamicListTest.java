package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 24.08.2018
 */
public class DynamicListTest {

    @Test
    public void whenAddElementAndGetFirstThenAddedElement() {
        DynamicList<String> list = new DynamicList<>();
        list.add("123");
        assertThat(list.get(0), is("123"));
    }

    @Test
    public void whenAddElementsMoreThenFiveThenSizeTen() {
        DynamicList<String> list = new DynamicList<>();
        assertThat(list.size(), is(5));
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        assertThat(list.size(), is(10));
    }

    @Test
    public void whenTakeNextThenFirstElement() {
        DynamicList<String> list = new DynamicList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(list.get(0)));
    }

    @Test
    public void whenTakeNextAfterChangeSizeThenException() {
        DynamicList<String> list = new DynamicList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(list.get(0)));
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
        DynamicList<String> list = new DynamicList<>();
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
        assertThat(iterator.next(), is(list.get(0)));
    }
}
