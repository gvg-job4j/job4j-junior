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
     * Первый элемент списка.
     */
    private Node<E> first;
    /**
     * Текущий последний элемент списка.
     */
    private Node<E> last;
    /**
     * Текущий размер хранилища.
     */
    private int size;
    /**
     * Счетчик изменений хранилища.
     */
    private int modCount;

    /**
     * Метод добавляет элемент в хранилище.
     *
     * @param element Добавляемый элемент.
     */
    public void add(E element) {
        Node<E> node = new Node<>(element);
        if (this.first == null) {
            this.first = node;
        } else {
            this.last.next = node;
        }
        this.last = node;
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
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }
        Node<E> item = this.first;
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item.object;
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
                if (!hasNext()) {
                    throw new NoSuchElementException();
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
