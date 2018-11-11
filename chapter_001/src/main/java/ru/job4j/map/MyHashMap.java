package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Valeriy Gyrievskikh
 * Реализация HashMap.
 * @since 08.10.2018
 */
public class MyHashMap<K, V> implements Iterable<K> {
    /**
     * Массив записей.
     */
    private Node<K, V>[] elements;
    /**
     * Текущий размер таблицы.
     */
    private int size;
    /**
     * Счетчик изменений данных.
     */
    private int modCount;
    /**
     * Значение по умолчанию, на которое выполняется изменение размера.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * Конструктор, создает таблицу с размером по умолчанию.
     */
    public MyHashMap() {
        this.elements = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Метод выполняет вставку значений.
     *
     * @param key   Ключ записи.
     * @param value Значение записи.
     * @return Результат выполнения вставки.
     */
    public boolean insert(K key, V value) {
        boolean inserted = false;
        if (key != null) {
            int index = indexFor(hash(key), this.elements.length);
            if (this.elements[index] == null) {
                this.elements[index] = new Node<>(key, value);
                inserted = true;
                this.modCount++;
                this.size++;
                if (this.size == this.elements.length) {
                    resize();
                }
            }
        }
        return inserted;
    }

    /**
     * Метод выполняет изменение размера таблицы.
     */
    private void resize() {
        Node<K, V>[] newElements = new Node[this.elements.length + DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < this.elements.length; i++) {
            int newIndex = indexFor(hash(this.elements[i].getKey()), newElements.length);
            newElements[newIndex] = this.elements[i];
        }
        this.elements = newElements;
    }

    /**
     * Метод выполняет получение значения по ключу.
     *
     * @param key Ключ таблицы.
     * @return Найденное значение или null.
     */
    public V get(K key) {
        Node<K, V> element = this.elements[indexFor(hash(key), this.elements.length)];
        return (element == null ? null : element.getValue());
    }

    /**
     * Метод выполняет удаление значения по указанному ключу.
     *
     * @param key Ключ таблицы.
     * @return Результат выполнения.
     */
    public boolean delete(K key) {
        boolean deleted = false;
        int index = indexFor(hash(key), this.elements.length);
        if (this.elements[index] != null) {
            this.elements[index] = null;
            deleted = true;
            this.modCount++;
            this.size--;
        }
        return deleted;
    }

    /**
     * Метод выполняет получение хеша по ключу.
     *
     * @param key Ключ таблицы.
     * @return Результат расчета.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return (h) ^ (h >>> 16);
    }

    /**
     * Метод определяет индекс ячейки, в которой хранится запись.
     *
     * @param hash   Результат расчета по хеш-коду ключа.
     * @param length Максимальный размер таблицы.
     * @return Индекс ячейки.
     */
    private int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    /**
     * Класс описывает запись карты.
     *
     * @param <K> Ключ записи.
     * @param <V> Значение записи.
     */
    private static class Node<K, V> {
        /**
         * Ключ записи.
         */
        private V value;
        /**
         * Значение записи.
         */
        private K key;

        /**
         * Конструктор, создает запись с указанными параметрами.
         *
         * @param key   Ключ записи.
         * @param value Значение записи.
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Метод возвращает значение записи.
         *
         * @return Значение.
         */
        public V getValue() {
            return value;
        }

        /**
         * Метод возвращает ключ записи.
         *
         * @return Ключ.
         */
        public K getKey() {
            return key;
        }
    }

    /**
     * Метод создает итератор для объекта.
     *
     * @return Итератор.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            /**
             * Счетчик изменений на момент создания итератора.
             */
            private final int currentModCount = modCount;
            /**
             * Счетчик итератора.
             */
            private int current;
            /**
             * Текущий индекс ячейки.
             */
            private int position;

            /**
             * Метод выполняет проверку на наличие следующей записи.
             * @return Результат проверки (true или false).
             */
            @Override
            public boolean hasNext() {
                return current < size;
            }

            /**
             * Метод выполняет получение значения следующей записи.
             * @return Значение записи.
             */
            @Override
            public K next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = null;
                for (; position < elements.length; position++) {
                    if (elements[position] != null) {
                        current++;
                        key = elements[position++].getKey();
                        break;
                    }
                }
                return key;
            }
        };
    }
}
