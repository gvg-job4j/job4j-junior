package ru.job4j.solid.lsp.eventloop.extended;

import ru.job4j.solid.lsp.eventloop.BaseFoodHandler;
import ru.job4j.solid.lsp.eventloop.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.07.2019
 */
public class Recycler extends ExtendedFoodHandler {

    /**
     * Список продуктов в текущем хранилище.
     */
    private List<Food> products = new ArrayList<>();

    /**
     * Метод добавляет выбранный продукт в хранилище.
     *
     * @param food Выбранный продукт.
     */
    private void addFood(Food food) {
        products.add(food);
    }

    /**
     * Метод возвращает список продуктов в текущем хранилище.
     *
     * @return Список продуктов.
     */
    public List<Food> getProducts() {
        return products;
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
        boolean stored = false;
        LocalDate date = LocalDate.now();
        LocalDate createDate = food.getCreateDate();
        LocalDate expiredDate = food.getExpiredDate();
        long daysBetweenNow = ChronoUnit.DAYS.between(date, expiredDate);
        long daysBetweenExpire = ChronoUnit.DAYS.between(createDate, expiredDate);
        long treshhold = (daysBetweenNow * 100) / daysBetweenExpire;
        if (treshhold <= 0 && ((ExtendedFood) food).isCanReproduct()) {
            addFood(food);
            stored = true;
        }
        return stored;
    }
}
