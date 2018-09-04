package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Valeriy Gyrievskikh
 * Реализация обертки над массивом.
 * @since 14.08.2018
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * Массив данных.
     */
    private T[] array;
    /**
     * Размер массива.
     */
    private int size;
    /**
     * Текущая свободная ячейка массива.
     */
    private int count;

    /**
     * Конструктор, создает массив заданного размера.
     *
     * @param length Размер массива.
     */
    public SimpleArray(int length) {
        array = (T[]) new Object[length];
        size = length;
        count = 0;
    }

    /**
     * Метод записывает объект в свободную ячейку массива.
     *
     * @param model Записываемый объект.
     */
    public void add(T model) {
        if (count < size) {
            setValue(count, model);
            count++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Метод записывает объект в ячейку с указанным индексом, если такая ячейка есть в массиве.
     *
     * @param index Индекс ячейки.
     * @param model Записываемый объект.
     */
    public void set(int index, T model) {
        if (index < size && index >= 0) {
            setValue(index, model);
            if (count == index) {
                count++;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Метод удаляет объект в указанной ячейке и сдвигает следующие объекты на одну позицию к началу массива.
     *
     * @param index Индекс ячейки, в которой нужно удалить объект.
     */
    public void delete(int index) {
        if (index < size && index >= 0) {
            try {
                if (size > index + 1) {
                    System.arraycopy(array, index + 1, array, index, size - (index + 1));
                }
                count--;
                array[size - 1] = null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Метод возвращает объект, находящийся в указанной ячейке.
     *
     * @param index Индекс ячейки.
     * @return Найденный объект, или null, если ячейка не существует, или пустая.
     */
    public T get(int index) {
        if (index < size && index >= 0) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Метод устанавливает объект в ячейку с указанным индексом.
     *
     * @param index Индекс ячейки.
     * @param model Устанавливаемый объект.
     */
    private void setValue(int index, T model) {
        try {
            array[index] = model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод возвращает итератор объекта.
     *
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentPosition = 0;

            /**
             * Метод проверяет наличие следщующего элемента коллекции.
             * @return Результат проверки.
             */
            @Override
            public boolean hasNext() {
                return currentPosition < size;
            }

            /**
             * Метод возвращает следщующий элемент коллекции.
             * @return Элемент.
             */
            @Override
            public T next() {
                if (currentPosition < size) {
                    return array[currentPosition++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
