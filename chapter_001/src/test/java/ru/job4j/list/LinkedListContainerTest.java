package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.08.2018
 */
public class LinkedListContainerTest {

    private LinkedListContainer<String> cont = new LinkedListContainer<>();
    private String first = "1";
    private String second = "2";
    private String third = "3";

    @Before
    public void fillList() {
        cont.add(first);
        cont.add(second);
        cont.add(third);
    }

    @Test
    public void whenAddThreeElementsAndGetThirdThenEqualThirdElement() {
        assertThat(cont.get(2), is(third));
    }

    @Test
    public void whenAddOneElementAndTakeNextThenEqualThisElement() {
        Iterator<String> iterator = cont.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(first));
    }

    @Test
    public void whenAddOneElementAndTakeTwoNextThenException() {
        Iterator<String> iterator = cont.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenGetIteratorAddElementTryNextThenException() {
        Iterator<String> iterator = cont.iterator();
        iterator.next();
        cont.add(first);
        try {
            iterator.next();
            fail("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }
}