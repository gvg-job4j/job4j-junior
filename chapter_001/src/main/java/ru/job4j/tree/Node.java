package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.11.2018
 */
public class Node<E extends Comparable<E>> {
    /**
     * Список подчиненных элементов.
     */
    private final List<Node<E>> children = new ArrayList<>();
    /**
     * Значение элемента.
     */
    private final E value;

    /**
     * Конструктор, создает элемент дерева с указанным значением.
     *
     * @param value Присваиваемое значение.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Метод добавляет элемент в список подчиненных элементов.
     *
     * @param child Подчиненный элемент.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Метод возвращает список подчиненных элементов.
     *
     * @return Список элементов.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод выполняет сравнение значения элемента с переданным значением.
     *
     * @param that Переданное значение.
     * @return Результат сравнения.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Метод возвращает значение элемента дерева.
     *
     * @return Значение элемента.
     */
    public E getValue() {
        return this.value;
    }

    /**
     * Метод выполняет сравнение элемента с переданным элементом.
     *
     * @param element Переданный элемент.
     * @return Результат сравнения.
     */
    public boolean equals(Object element) {
        return (element instanceof Node && this.value.equals(((Node<E>) element).value));
    }

    /**
     * Метод возвращает хеш-код по значению элемента.
     *
     * @return Хеш-код.
     */
    public int hashCode() {
        return this.value.hashCode();
    }
}