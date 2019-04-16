package ru.job4j.solid.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class Thrash implements TransferStrategy {
    /**
     * Список еды в текущем хранилище.
     */
    private List<Food> products = new ArrayList<>();

    /**
     * Метод добавляет выбранную еду в хранилище.
     *
     * @param food Выбранная еда.
     */
    @Override
    public void addFood(Food food) {
        products.add(food);
    }

    /**
     * Метод возвращает список еды в текущем хранилище.
     *
     * @return Список еды.
     */
    public List<Food> getProducts() {
        return products;
    }
}
