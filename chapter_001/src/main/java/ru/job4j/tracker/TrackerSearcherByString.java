package ru.job4j.tracker;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh.
 * @since 28.07.2018.
 */
public class TrackerSearcherByString {
    /**
     * Метод выполняет поиск в заданном списке по указанному значению.
     *
     * @param string  Значение, по которому выполняется поиск.
     * @param list    Список, в котором выполняется поиск.
     * @param position Текущий размер списка.
     * @param seacher Функция, используемая для поска.
     * @return Список найденных значений.
     */
    public List<Item> findBy(String string, List<Item> list, int position, TrackerSearcher<Item> seacher) {
        return seacher.findBy(string, list, position);
    }

}
