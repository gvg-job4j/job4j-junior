package ru.job4j.iterator;

import java.util.*;
import java.lang.Integer;

/**
 * @author Valeriy Gyrievskikh
 * Итератор для двухмерного массива
 * @since 09.08.2018
 */
public class MatrixIterator implements Iterator<Integer> {

    /**
     * Обрабатываемый массив.
     */
    private int[][] array;
    /**
     * Текущая строка массива.
     */
    private int currentRow;
    /**
     * Текущая колонка массива.
     */
    private int currentColumn;
    /**
     * Текущий индекс.
     */
    private int currentIndex;
    /**
     * Количество элементов в массиве.
     */
    private long size;

    /**
     * Конструктор класса.
     *
     * @param array Обрабатываемый массив.
     */
    public MatrixIterator(int[][] array) {
        this.array = array;
        this.currentRow = 0;
        this.currentColumn = 0;
        this.currentIndex = 0;
        this.size = Arrays.stream(array).flatMapToInt(x -> Arrays.stream(x)).count();
    }

    /**
     * Метод проверяет, есть ли следующий элемент.
     *
     * @return Результат проверки.
     */
    @Override
    public boolean hasNext() {
        return this.currentIndex < this.size;
    }

    /**
     * Метод возвращает следующий элемент массива.
     *
     * @return Элемент массива.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int element = array[currentRow][currentColumn];
        if (array[currentRow].length == currentColumn + 1) {
            currentRow++;
            currentColumn = 0;
        } else {
            currentColumn++;
        }
        this.currentIndex++;
        return element;
    }
}
