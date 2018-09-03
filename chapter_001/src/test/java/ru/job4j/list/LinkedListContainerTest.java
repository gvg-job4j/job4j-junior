package ru.job4j.list;

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

    @Test
    public void whenAddOneElementAndGetFirstThenEqualThisElement(){
        LinkedListContainer<String> cont = new LinkedListContainer<>();
        String first = "1";
        cont.add(first);
        assertThat(cont.get(0), is(first));
    }

    @Test
    public void whenAddOneElementAndTakeNextThenEqualThisElement(){
        LinkedListContainer<String> cont = new LinkedListContainer<>();
        String first = "1";
        cont.add(first);
        Iterator<String> iterator = cont.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(first));
    }

    @Test
    public void whenAddOneElementAndTakeTwoNextThenException(){
        LinkedListContainer<String> cont = new LinkedListContainer<>();
        String first = "1";
        cont.add(first);
        Iterator<String> iterator = cont.iterator();
        iterator.next();
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenGetIteratorAddElementTryNextThenException(){
        LinkedListContainer<String> cont = new LinkedListContainer<>();
        String first = "1";
        cont.add(first);
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