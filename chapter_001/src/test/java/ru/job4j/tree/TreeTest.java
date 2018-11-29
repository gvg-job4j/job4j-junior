package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void whenSixElFindLastThenSix() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void whenSixElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTryAddSameChildrenThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.add(5, 6),
                is(false)
        );
    }

    @Test
    public void whenIteratorHasNextThenNext() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 7);
        tree.add(5, 6);
        tree.add(4, 8);
        tree.add(6, 10);
        tree.add(6, 11);
        tree.add(5, 9);
        tree.add(10, 12);
        Iterator<Integer> myIterator = tree.iterator();
        while (myIterator.hasNext()) {
            Integer value = myIterator.next();
            System.out.println(value);
            assertNotNull(value);
        }
    }

    @Test
    public void whenGetNextThenOne() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> myIterator = tree.iterator();
        assertThat(myIterator.next(), is(1));
    }

    @Test
    public void whenGetIteratorAndAddElementThenException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        Iterator<Integer> myIterator = tree.iterator();
        tree.add(4, 5);
        tree.add(5, 6);
        try {
            myIterator.next();
            fail("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenChildSizeMoreThenTwoThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.isBinary());
    }

    @Test
    public void whenChildSizeLessThenTwoThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        tree.add(7, 8);
        tree.add(2, 9);
        assertTrue(tree.isBinary());
    }
}
