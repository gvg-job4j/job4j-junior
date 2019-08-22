package ru.job4j.solid.lsp.eventloop;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 22.08.2019
 */
public interface Redistributable extends FoodHandler {
    /**
     * Метод выполняет получение списка продуктов, находящихся в хранилище.
     *
     * @return Список продуктов.
     */
    List<Food> getProducts();
}
