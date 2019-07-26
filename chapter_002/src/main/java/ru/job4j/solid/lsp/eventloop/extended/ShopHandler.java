package ru.job4j.solid.lsp.eventloop.extended;

import ru.job4j.solid.lsp.eventloop.Food;
import ru.job4j.solid.lsp.eventloop.Shop;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.07.2019
 */
public class ShopHandler extends ExtendedFoodHandler {

    /**
     * Расширяемое хранилище.
     */
    private Shop handler;

    /**
     * Конструктор, устанавливает текущее хранилище.
     *
     * @param handler Текущее хранилище
     */
    public ShopHandler(Shop handler) {
        this.handler = handler;
    }

    /**
     * Метод определеяет, выполняются ли требования для добавления в хранилище
     * переданного продукта.
     *
     * @param food Переданный продукт.
     * @return Результат проверки (true или false).
     */
    @Override
    public boolean transfer(Food food) {
        return handler.transfer(food);
    }

    /**
     * Метод возвращает список продуктов в текущем хранилище.
     *
     * @return Список продуктов.
     */
    public List<Food> getProducts() {
        return handler.getProducts();
    }
}