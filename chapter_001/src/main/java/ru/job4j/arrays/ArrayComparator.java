package ru.job4j.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Valeriy Gyrievskikh
 * @since 13.04.2019
 */
public class ArrayComparator {

    /**
     * Метод выполняет сравнение двух массивов по содержимому.
     *
     * @param array1 Первый массив.
     * @param array2 Второй массив.
     * @return Результат сравнения (true или false).
     */
    public boolean compare(Object[] array1, Object[] array2) {
        boolean isSame = false;
        if (array1.length == array2.length) {
            ArrayList<Object> compareList = new ArrayList<>();
            Collections.addAll(compareList, array2);
            compareList.trimToSize();
            for (int i = 0; i < array1.length; i++) {
                for (int j = 0; j < compareList.size(); j++) {
                    if (array1[i].equals(compareList.get(j))) {
                        compareList.remove(j);
                        break;
                    }
                }
            }
            isSame = (compareList.size() == 0);
        }
        return isSame;
    }
}
