package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.08.2018
 */
public class SimpleArrayListTest {
    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteElementThenGetSecondElement() {
        list.delete();
        assertThat(list.get(list.getSize() - 1), is(2));
        assertThat(list.getSize(), is(2));
    }

}
