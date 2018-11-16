package ru.job4j.tree;

import java.util.Optional;

/**
 * @author Valeriy Gyrievskikh
 * Реализация Tree.
 * @since 11.11.2018
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Метод добавляет элемент в список подчиненных элементов.
     *
     * @param parent Значение главного элемента.
     * @param child  Значение подчиненного элемента.
     * @return Результат добавления.
     */
    boolean add(E parent, E child);

    /**
     * Метод выполняет поиск элемента дерева по значению.
     *
     * @param value Значение элемента.
     * @return Найденный элемент или пустой элемент.
     */
    Optional<Node<E>> findBy(E value);
}
