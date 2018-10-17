package ru.job4j.map;

import java.util.Arrays;
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
     * Массив значений.
     */
    private V[] values;
    /**
     * Массив ключей.
     */
    private K[] keys;
    /**
     * Текущий размер таблицы.
     */
    private int size;
    /**
     * Максимальный размер таблицы.
     */
    private int length;
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
        this.values = ((V[]) new Object[DEFAULT_INITIAL_CAPACITY]);
        this.keys = ((K[]) new Object[DEFAULT_INITIAL_CAPACITY]);
        resize();
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
            int index = indexFor(hash(key), this.length);
            if (this.keys[index] == null) {
                this.keys[index] = key;
                this.values[index] = value;
                inserted = true;
                this.size++;
                if (this.size == this.length) {
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
        this.length += DEFAULT_INITIAL_CAPACITY;
        if (this.length > 16) {
            modCount++;
            this.values = Arrays.copyOf(this.values, this.length);
            this.keys = Arrays.copyOf(this.keys, this.length);
            for (int i = 0; i < this.length; i++) {
                int newIndex = indexFor(hash(this.keys[i]), this.length);
                if (newIndex != i) {
                    this.keys[newIndex] = this.keys[i];
                    this.values[newIndex] = this.values[i];
                    this.keys[i] = null;
                    this.values[i] = null;
                }
            }
        }
    }

    /**
     * Метод выполняет получение значения по ключу.
     *
     * @param key Ключ таблицы.
     * @return Найденное значение или null.
     */
    public V get(K key) {
        return this.values[indexFor(hash(key), this.length)];
    }

    /**
     * Метод выполняет удаление значения по указанному ключу.
     *
     * @param key Ключ таблицы.
     * @return Результат выполнения.
     */
    public boolean delete(K key) {
        boolean deleted = false;
        int index = indexFor(hash(key), this.length);
        if (this.keys[index] != null) {
            this.keys[index] = null;
            this.values[index] = null;
            deleted = true;
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
             * Текущий индекс ячейки.
             */
            private int current;

            /**
             * Метод выполняет проверку на наличие следующей записи.
             * @return Результат проверки (true или false).
             */
            @Override
            public boolean hasNext() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current < length;
            }

            /**
             * Метод выполняет получение значения следующей записи.
             * @return Значение записи.
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = null;
                for (int i = current; i < length; i++) {
                    current++;
                    if (keys[i] != null) {
                        key = keys[i];
                        break;
                    }
                }
                return key;
            }
        };
    }
}
