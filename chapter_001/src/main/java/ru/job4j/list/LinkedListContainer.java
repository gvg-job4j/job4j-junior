package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создание контейнера на базе связанного списка.
 *
 * @author Valeriy Gyrievskikh
 * @since 29.08.2018
 */
public class LinkedListContainer<E> implements Iterable<E> {
    /**
     * Текущий элемент, к которому нет привязки.
     */
    private Node<E> first;
    /**
     * Текущий размер хранилища.
     */
    private int size;
    /**
     * Счетчик изменений хранилища.
     */
    private int modCount = 0;

    /**
     * Метод добавляет элемент в хранилище.
     *
     * @param element Добавляемый элемент.
     */
    public void add(E element) {
        Node<E> node = new Node<>(element);
        node.next = this.first;
        this.first = node;
        size++;
        modCount++;
    }

    /**
     * Метод возвращает элемент по индексу.
     *
     * @param index Индекс элемента.
     * @return Найденный элемент или исключение.
     */
    public E get(int index) {
        if (index < size) {
            Node<E> item = this.first;
            for (int i = 0; i < index; i++) {
                item = item.next;
            }
            return item.object;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Метод создает итератор для хранилища.
     *
     * @return Итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Количество структурных изменений на момент создания итератора.
             */
            private final int currentModCount = modCount;
            /**
             * Текущий индекс хранилища по итератору.
             */
            private int count = 0;

            /**
             * Метод проверяет наличие следующего элемента.
             * @return Результат проверки (true - есть следующий элемент, false - нет).
             */
            @Override
            public boolean hasNext() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            /**
             * Метод возвращает следующий объект хранилища.
             * @return Объект, или exception, если следующего объекта нет.
             */
            @Override
            public E next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(count++);
            }
        };
    }

    /**
     * Класс предназначен для хранения данных.
     *
     * @param <E> тип хранимых данных.
     */
    private static class Node<E> {
        /**
         * Хранимые данные.
         */
        private E object;
        /**
         * Следующий элемент коллекции.
         */
        Node<E> next;

        /**
         * Метод вставляет данные в элемент.
         *
         * @param object Вставляемые данные.
         */
        public Node(E object) {
            this.object = object;
        }

    }
}
