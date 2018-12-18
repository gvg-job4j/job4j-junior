package ru.job4j.statistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Valeriy Gyrievskikh
 * @since 21.11.2018
 */
public class Info {
    /**
     * Количество добавленных элементов.
     */
    private int added;
    /**
     * Количество удаленных элементов.
     */
    private int deleted;
    /**
     * Количество измененных элементов.
     */
    private int changed;

    /**
     * Метод возвращает количество добавленных элементов.
     *
     * @return Количество добавленных элементов.
     */
    public int getAdded() {
        return added;
    }

    /**
     * Метод возвращает количество удаленных элементов.
     *
     * @return Количество удаленных элементов.
     */
    public int getDeleted() {
        return deleted;
    }

    /**
     * Метод возвращает количество измененных элементов.
     *
     * @return Количество измененных элементов.
     */
    public int getChanged() {
        return changed;
    }

    /**
     * Метод выполняет сравнение переданных коллекций и заполняет значения объекта.
     *
     * @param previous Коллекция с начальными данными.
     * @param current  Коллекция с текущими данными.
     */
    public void fillParameters(List<Store.User> previous, List<Store.User> current) {
        if (previous.size() == 0) {
            this.added = current.size();
        } else if (current.size() == 0) {
            this.deleted = previous.size();
        } else {
            Map<Integer, Store.User> prevMap = previous.stream().collect(
                    Collectors.toMap(Store.User::getId, user -> user));
            for (Store.User value : current) {
                Store.User user = prevMap.remove(value.getId());
                if (user == null) {
                    this.added++;
                } else {
                    if (!user.getName().equals(value.getName())) {
                        this.changed++;
                    }
                }
            }
            this.deleted = prevMap.size();
        }
    }
}
