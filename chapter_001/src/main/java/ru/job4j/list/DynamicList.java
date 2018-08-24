package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Valeriy Gyrievskikh
 * Динамический список на базе массива.
 * @since 23.08.2018
 */
public class DynamicList<E> implements Iterable<E> {
    /**
     * Хранилище данных.
     */
    private E[] array;
    /**
     * Текущая позиция.
     */
    private int count;
    /**
     * Текущее количество структурных изменений.
     */
    private int modCount;

    /**
     * Конструктор, создает хранилище размером 5.
     */
    public DynamicList() {
        this.count = 0;
        this.array = (E[]) new Object[5];
        this.modCount = 0;
    }

    /**
     * Метод добавляет объект в хранилище.
     *
     * @param value Добавляемый объект.
     */
    public void add(E value) {
        if (this.count >= this.array.length) {
            addSize();
        }
        this.array[this.count] = value;
        this.count++;
    }

    /**
     * Метод получает объект по индексу.
     *
     * @param index Индекс хранилища.
     * @return Найденный объект или null.
     */
    public E get(int index) {
        E value = null;
        if (index < this.array.length) {
            value = this.array[index];
        }
        return value;
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
            private final int currentCount = modCount;

            /**
             * Текущий индекс хранилища по итератору.
             */
            private int current = 0;

            /**
             * Метод проверяет наличие следующего элемента.
             * @return Результат проверки (true - есть следующий элемент, false - нет).
             */
            @Override
            public boolean hasNext() {
                if (currentCount == modCount) {
                    return current < array.length;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            /**
             * Метод возвращает следующий объект хранилища.
             * @return Объект, или exception, если следующего объекта нет.
             */
            @Override
            public E next() {
                if (currentCount == modCount) {
                    if (hasNext()) {
                        return array[current++];
                    } else {
                        throw new NoSuchElementException();
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Метод возвращает текущий размер хранилища.
     *
     * @return Размер хранилища.
     */
    public int size() {
        return array.length;
    }

    /**
     * Метод увеличивает размер хранилища.
     */
    private void addSize() {
        this.array = Arrays.copyOf(this.array, this.array.length + 5);
        this.modCount++;
    }
}
