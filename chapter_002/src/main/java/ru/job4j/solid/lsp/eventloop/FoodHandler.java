package ru.job4j.solid.lsp.eventloop;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 05.06.2019
 */
public interface FoodHandler {
    /**
     * Метод добавляет хранилище в список.
     */
    void setNext(FoodHandler handler);

    /**
     * Метод выполняет распределение продуктов по хранилищам.
     *
     * @param food Поступивший продукт.
     * @return Результат распределения в текущее хранилище (true или false).
     */
    boolean transfer(Food food);

    /**
     * Метод возвращает текущий список хранилищ.
     *
     * @return Список хранилищ.
     */
    List<FoodHandler> getStores();

    /**
     * Метод возвращает текущее хранилище.
     *
     * @return Текущее хранилище.
     */
    FoodHandler getNext();
}
