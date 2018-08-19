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
             * Метод проверяет наличие следующего элемента.
             * @return Результат проверки.
             */
            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                if (iterator == null) {
                    hasNext = checkParentHasNext();
                } else {
                    if (iterator.hasNext()) {
                        hasNext = true;
                    } else {
                        hasNext = checkParentHasNext();
                    }
                }
                return hasNext;
            }

            /**
             * Метод возвращает следующий элемент.
             * @return Элемент итератора.
             */
            @Override
            public Integer next() {
                int number = 0;
                if (iterator == null) {
                    number = checkParentNext();
                } else {
                    if (iterator.hasNext()) {
                        number = iterator.next();
                    } else {
                        number = checkParentNext();
                    }
                }
                return number;
            }

            /**
             * Метод проверяет наличие следующего элемента у родительского итератора.
             * @return Результат проверки.
             */
            private boolean checkParentHasNext() {
                boolean hasNext = false;
                if (parentIterator.hasNext()) {
                    iterator = parentIterator.next();
                    if (iterator.hasNext()) {
                        hasNext = true;
                    }
                }
                return hasNext;
            }

            /**
             * Метод получает данные из следующего итератора.
             * @return Элемент итератора.
             */
            private int checkParentNext() {
                int number = 0;
                if (parentIterator.hasNext()) {
                    iterator = parentIterator.next();
                    if (iterator.hasNext()) {
                        number = iterator.next();
                    } else {
                        throw new NoSuchElementException();
                    }
                } else {
                    throw new NoSuchElementException();
                }
                return number;
            }
        };
    }
}
