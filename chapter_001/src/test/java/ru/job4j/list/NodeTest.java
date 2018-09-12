package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Valeriy Gyrievskikh
 * @since 05.09.2018
 */
public class NodeTest {

    @Test
    public void whenHasCycleThenTrue() {
        Node first = new Node<>(1);
        Node two = new Node<>(2);
        Node third = new Node<>(3);
        Node four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        Assert.assertTrue(Node.hasCycle(first));
    }

    @Test
    public void whenHasCycleInMiddleThenTrue() {
        Node first = new Node<>(1);
        Node two = new Node<>(2);
        Node third = new Node<>(3);
        Node four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = two;
        four.next = null;
        Assert.assertTrue(Node.hasCycle(first));
    }

    @Test
    public void whenHasNoCycleThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        Assert.assertFalse(Node.hasCycle(first));
    }
}
