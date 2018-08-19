package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;

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
        this.array = (T[]) new Object[length];
        this.size = length;
        this.count = 0;
    }

    /**
     * Метод записывает объект в свободную ячейку массива.
     *
     * @param model Записываемый объект.
     */
    public void add(T model) {
        if (count < size) {
            setValue(count, model);
            this.count++;
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
            if (this.count == index) {
                this.count++;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Метод удаляет объект в указанной ячейке и свигает следующие объекты на одну позицию к началу массива.
     *
     * @param index Иднекс ячейки, в которой нужно удалить объект.
     */
    public void delete(int index) {
        if (index < size && index >= 0) {
            try {
                if (size > index + 1) {
                    System.arraycopy(this.array, index + 1, this.array, index, size - (index + 1));
                }
                count--;
                this.array[size - 1] = null;
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
        T element = null;
        if (index < size && index >= 0) {
            element = this.array[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
        return element;
    }

    /**
     * Метод устанавливает объект в ячейку с у казанным индексом.
     *
     * @param index Индекс ячейки.
     * @param model Устанавливаемый объект.
     */
    private void setValue(int index, T model) {
        try {
            this.array[index] = model;
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
        return Arrays.asList(array).iterator();
    }
}
