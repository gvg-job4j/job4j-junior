package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Valeriy Gyrievskikh
 * Обработка итератора, содержащего другие итераторы.
 * @since 13.08.2018
 */
public class Converter {
    /**
     * Метод возвращает последовательность вложенных итераторов.
     *
     * @param it Обрабатываемый итератор.
     * @return Последовательность данных.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> iterator = null;
            Iterator<Iterator<Integer>> parentIterator = it;

            /**
             * Метод проверяет наличие следующего элемента итератора.
             * @return Результат проверки.
             */
            @Override
            public boolean hasNext() {
                return checkIteratorHasNext();
            }

            /**
             * Метод возвращает следующий элемент итератора.
             * @return Элемент итератора.
             */
            @Override
            public Integer next() {
                return checkIteratorNext();
            }

            /**
             * Метод проверяет наличие следующего элемента итератора.
             * @return Результат проверки.
             */
            private boolean checkIteratorHasNext() {
                boolean hasNext = false;
                if (iterator != null && iterator.hasNext()) {
                    hasNext = true;
                } else if (parentIterator.hasNext()) {
                    iterator = parentIterator.next();
                    hasNext = iterator.hasNext();
                }
                return hasNext;
            }

            /**
             * Метод возвращает следующий элемент итератора.
             * @return Элемент итератора.
             */
            private int checkIteratorNext() {
                int number = 0;
                if (iterator != null && iterator.hasNext()) {
                    number = iterator.next();
                } else {
                    if (parentIterator.hasNext()) {
                        iterator = parentIterator.next();
                        number = checkIteratorNext();
                    } else {
                        throw new NoSuchElementException();
                    }
                }
                return number;
            }
        };
    }
}
