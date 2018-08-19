package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.Is.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 14.08.2018
 */
public class SimpleArrayTest {

    @Test
    public void whenAddNewElementAddGet() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("text");
        assertThat(array.get(0), is("text"));
    }

    @Test
    public void whenGetLast() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("text1");
        array.add("text2");
        array.add("text3");
        assertThat(array.get(2), is("text3"));
    }

    @Test
    public void whenIndexBiggerThenSize() {
        try {
            SimpleArray<String> array = new SimpleArray<>(2);
            array.add("text1");
            array.set(2, "text2");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenSetByIndex() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("text1");
        array.add("text2");
        array.add("text3");
        array.add("text4");
        array.add("text5");
        array.set(3, "text44");
        assertThat(array.get(3), is("text44"));
    }

    @Test
    public void whenDeleteByIndex() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("text1");
        array.add("text2");
        array.add("text3");
        array.add("text4");
        array.add("text5");
        array.delete(1);
        assertThat(array.get(3), is("text5"));
    }

    @Test
    public void whenDeleteByIncorrectIndex() {
        SimpleArray<String> array = new SimpleArray<>(2);
        try {
            array.add("text1");
            array.add("text2");
            array.delete(3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenAddDeleteAdd() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("text1");
        array.add("text2");
        array.delete(1);
        array.add("text3");
        String result = array.get(1);
        assertThat(result, is("text3"));
    }

    @Test
    public void whenIteratorNext() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("text1");
        array.add("text2");
        Iterator<String> iterator = array.iterator();
        String result = iterator.next();
        String expect = array.get(0);
        assertThat(result, is(expect));
    }
}