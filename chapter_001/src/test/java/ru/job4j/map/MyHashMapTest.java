package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 09.10.2018
 */
public class MyHashMapTest {
    MyHashMap<String, String> map;

    @Before
    public void setupMyMap() {
        map = new MyHashMap<>();
        for (int i = 0; i < 1270; i++) {
            map.insert(Integer.toString(i), "User" + Integer.toString(i));
        }
    }

    @Test
    public void whenAddSameElementThenFalse() {
        assertFalse(map.insert("5", "User5"));
    }

    @Test
    public void whenGetElementThenTrue() {
        assertThat(map.get("8"), is("User8"));
    }

    @Test
    public void whenDeleteElementThenTrue() {
        assertTrue(map.delete("1"));
        assertNull(map.get("1"));
    }

    @Test
    public void whenIteratorHasNextThenNext() {
        Iterator<String> myIterator = map.iterator();
        while (myIterator.hasNext()) {
            assertNotNull(myIterator.next());
        }
    }

    @Test
    public void whenNoNextThenException() {
        Iterator<String> myIterator = map.iterator();
        while (myIterator.hasNext()) {
            assertNotNull(myIterator.next());
        }
        try {
            myIterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void whenChangeMapAndGetNextThenException() {
        Iterator<String> myIterator = map.iterator();
        map.delete("1");
        try {
            myIterator.next();
            fail("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            assertNotEquals("", e.getMessage());
        }
    }
}
