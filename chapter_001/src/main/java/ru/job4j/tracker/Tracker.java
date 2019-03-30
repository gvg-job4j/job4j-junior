package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 19.06.2018.
 */
public class Tracker implements ITracker {
    /**
     * Список для хранения заявок.
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод, реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(this.position++, item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        return new Date().toString() + Math.random();
    }

    /**
     * Метод, реализующий замену заявки в хранилище.
     *
     * @param id   Идентификатор заявки.
     * @param item Заявка.
     */
    public void replace(String id, Item item) {
        if (position != 0) {
            for (int i = 0; i < position; i++) {
                if (items.get(i).getId().equals(id)) {
                    item.setId(id);
                    items.set(i, item);
                    break;
                }
            }
        }
    }

    /**
     * Метод, реализующий удаление заявки.
     *
     * @param id Идентификатор заявки.
     */
    public void delete(String id) {
        if (position != 0) {
            for (int i = 0; i < position; i++) {
                if (items.get(i).getId().equals(id)) {
                    items.remove(i);
                    items.trimToSize();
                    position--;
                    break;
                }
            }
        }
    }

    /**
     * Метод, возвращающий список заявок.
     *
     * @return Список заявок без пустых значений или null.
     */
    public List<Item> findAll() {
        return new TrackerSearcherByString().findBy(null, items, position, (string, list, pointer) -> {
            ArrayList<Item> itemsList = null;
            if (pointer != 0) {
                itemsList = (ArrayList<Item>) list;
                itemsList.trimToSize();
            }
            return itemsList;
        });
    }

    /**
     * Метод, возвращающий список заявок по названию.
     *
     * @param key Название заявки.
     * @return Список заявок с одинаковым именем или null.
     */
    public List<Item> findByName(String key) {
        return new TrackerSearcherByString().findBy(key, items, position, (string, list, pointer) -> {
            ArrayList<Item> itemsByName = null;
            if (pointer != 0) {
                itemsByName = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == null) {
                        break;
                    }
                    if (list.get(i).getName().equals(string)) {
                        itemsByName.add(list.get(i));
                    }
                }
            }
            return itemsByName;
        });
    }

    /**
     * Метод, возвращающий заявку по идентификатору.
     *
     * @param id Идентификатор заявки.
     * @return Найденная заявка или null.
     */
    public Item findById(String id) {
        List<Item> listFromId = new TrackerSearcherByString().findBy(id, items, position, (string, list, pointer) -> {
            ArrayList<Item> itemsById = new ArrayList<>();
            for (int i = 0; i < pointer; i++) {
                if (list.get(i).getId().equals(string)) {
                    itemsById.add(list.get(i));
                    break;
                }
            }
            return itemsById;
        });
        return listFromId.size() == 0 ? null : listFromId.get(0);
    }
}
