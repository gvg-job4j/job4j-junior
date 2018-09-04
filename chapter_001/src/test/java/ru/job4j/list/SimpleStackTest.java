package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.09.2018
 */
public class SimpleStackTest {
    private SimpleStack<String> stack;

    @Before
    public void createStack() {
        stack = new SimpleStack<>();
    }

    @Test
    public void whenPushThreeElementsThenPollThreeElementsInReverseOrder() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        assertThat(stack.poll(), is("3"));
        assertThat(stack.poll(), is("2"));
        assertThat(stack.poll(), is("1"));
    }

    @Test
    public void whenPushTwoElementsAndPollThreeThenException() {
        stack.push("1");
        stack.push("2");
        assertThat(stack.poll(), is("2"));
        assertThat(stack.poll(), is("1"));
        try {
            stack.poll();
            fail("Expected NullPointerException");
        } catch (NullPointerException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }
}
