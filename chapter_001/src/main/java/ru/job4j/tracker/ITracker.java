package ru.job4j.tracker;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 30.03.2019
 */
public interface ITracker {
    /**
     * Метод, реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    Item add(Item item);

    /**
     * Метод, реализующий замену заявки в хранилище.
     *
     * @param id   Идентификатор заявки.
     * @param item Заявка.
     */
    void replace(String id, Item item);

    /**
     * Метод, реализующий удаление заявки.
     *
     * @param id Идентификатор заявки.
     */
    void delete(String id);

    /**
     * Метод, возвращающий список заявок.
     *
     * @return Список заявок без пустых значений или null.
     */
    List<Item> findAll();

    /**
     * Метод, возвращающий список заявок по названию.
     *
     * @param key Название заявки.
     * @return Список заявок с одинаковым именем или null.
     */
    List<Item> findByName(String key);

    /**
     * Метод, возвращающий заявку по идентификатору.
     *
     * @param id Идентификатор заявки.
     * @return Найденная заявка или null.
     */
    Item findById(String id);
}
