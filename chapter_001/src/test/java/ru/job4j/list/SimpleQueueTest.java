package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.09.2018
 */
public class SimpleQueueTest {
    private SimpleQueue<String> queue;

    @Before
    public void createQueue() {
        queue = new SimpleQueue<>();
    }

    @Test
    public void whenPushThreeElementsThenPollThreeElementsInDirectOrder() {
        queue.push("1");
        queue.push("2");
        queue.push("3");
        assertThat(queue.poll(), is("1"));
        assertThat(queue.poll(), is("2"));
        assertThat(queue.poll(), is("3"));
    }

    @Test
    public void whenPushTwoElementsAndPollThreeThenException() {
        queue.push("1");
        queue.push("2");
        assertThat(queue.poll(), is("1"));
        assertThat(queue.poll(), is("2"));
        try {
            queue.poll();
            fail("Expected NullPointerException");
        } catch (NullPointerException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }
}
