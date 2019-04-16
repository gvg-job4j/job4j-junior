package ru.job4j.solid.lsp;

import java.util.Date;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class ControlQuality {
    /**
     * Склад.
     */
    private Warehouse warehouse;
    /**
     * Магазин.
     */
    private Shop shop;
    /**
     * Мусорка.
     */
    private Thrash thrash;

    /**
     * Конструктор, устанавливает используемые места хранения.
     *
     * @param warehouse Склад.
     * @param shop      Магазин.
     * @param thrash    Мусорка.
     */
    public ControlQuality(Warehouse warehouse, Shop shop, Thrash thrash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.thrash = thrash;
    }

    /**
     * Метод выполняет распределение продуктов по местам хранения в зависимости от
     * срока годности продукта.
     *
     * @param food Распределяемый продукт.
     */
    public void sendToStore(Food food) {
        long nowTime = new Date().getTime();
        long createTime = food.getCreateDate().getTime();
        long expiredTime = food.getExpiredDate().getTime();
        int treshhold = (int) (((double) (expiredTime - nowTime) / (double) (expiredTime - createTime)) * 100);
        if (treshhold > 75) {
            new Context(warehouse).executeStrategy(food);
        } else if (treshhold < 75 && treshhold > 25) {
            new Context(shop).executeStrategy(food);
        } else if (treshhold < 25 && treshhold > 0) {
            food.setPrice(food.getPrice() - food.getDiscount());
            new Context(shop).executeStrategy(food);
        } else {
            new Context(thrash).executeStrategy(food);
        }
    }
}
