package ru.job4j.arrays;

import java.util.HashMap;

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
            HashMap<Object, Integer> map1 = makeMapFromArray(array1);
            HashMap<Object, Integer> map2 = makeMapFromArray(array2);
            isSame = map1.equals(map2);
        }
        return isSame;
    }

    /**
     * Метод создает объект HashMap на основаннии данных массива.
     *
     * @param array Массив для заполнения HashMap.
     * @return Полученный HashMap.
     */
    private HashMap<Object, Integer> makeMapFromArray(Object[] array) {
        HashMap<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer value = 1;
            if (map.containsKey(array[i])) {
                value = map.get(array[i]) + 1;
            }
            map.put(array[i], value);
        }
        return map;
    }
}
