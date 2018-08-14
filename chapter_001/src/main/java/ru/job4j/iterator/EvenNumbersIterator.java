package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Valeriy Gyrievskikh
 * Реализация итератора, возвращающего только четные числа.
 * @since 13.08.2018
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Обрабатываемый массив.
     */
    private int[] array;
    /**
     * Текущая позиция.
     */
    private int count;

    /**
     * Конструктор класса.
     * @param array
     */
    public EvenNumbersIterator(int[] array) {
        this.array = array;
        this.count = 0;
    }

    /**
     * Метод проверяет наличие следующего четного числа в массиве.
     * @return Результат проверки.
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        int currentCount = this.count;
        while (currentCount < this.array.length) {
            if (this.array[currentCount] % 2 == 0) {
                hasNext = true;
                break;
            } else {
                currentCount++;
            }
        }
        return hasNext;
    }

    /**
     * Метод возвращает следующий элемент массива, содержащий четное число.
     * @return Элемент массива.
     */
    @Override
    public Integer next() {
        int number = 0;
        while (true) {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            number = this.array[this.count];
            this.count++;
            if (number % 2 == 0) {
                break;
            }
        }
        return number;
    }
}
