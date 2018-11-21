package ru.job4j.statistic;

import java.util.ArrayList;
import java.util.List;

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
            List<Store.User> lookedList = new ArrayList<>();
            for (int i = 0; i < previous.size(); i++) {
                if (!current.contains(previous.get(i))) {
                    this.deleted++;
                } else {
                    for (Store.User element : current) {
                        if (element.getId() == previous.get(i).getId()) {
                            if (!element.getName().equals(previous.get(i).getName())) {
                                this.changed++;
                            }
                            break;
                        }
                    }
                    lookedList.add(previous.get(i));
                }
            }
            for (int i = 0; i < current.size(); i++) {
                if (!lookedList.contains(current.get(i)) && !previous.contains(current.get(i))) {
                    this.added++;
                }
            }
        }
    }
}
