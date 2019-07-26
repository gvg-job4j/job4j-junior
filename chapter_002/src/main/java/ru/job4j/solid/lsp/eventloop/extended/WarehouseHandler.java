package ru.job4j.solid.lsp.eventloop.extended;

import ru.job4j.solid.lsp.eventloop.Food;
import ru.job4j.solid.lsp.eventloop.Warehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.07.2019
 */
public class WarehouseHandler extends ExtendedFoodHandler {

    /**
     * Расширяемое хранилище.
     */
    private Warehouse handler;
    /**
     * Признак добавления нового склада.
     */
    private boolean needMore = false;
    /**
     * Максимальный размер хранилища.
     */
    private int maxSpace;

    /**
     * Конструктор, устанавливает текущее хранилище и максимальный размер хранилища.
     *
     * @param handler  Текущее хранилище.
     * @param maxSpace Максимальный размер хранилища.
     */
    public WarehouseHandler(Warehouse handler, int maxSpace) {
        this.handler = handler;
        this.maxSpace = maxSpace;
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
        if (treshhold > 75 && !((ExtendedFood) food).isVegetable()) {
            needMore = false;
            if (handler.getProducts().size() < maxSpace) {
                handler.addFood(food);
                stored = true;
            }
            if (stored && (handler.getProducts().size() == maxSpace)) {
                needMore = true;
            }
        }
        return stored;
    }

    /**
     * Метод возвращает список продуктов в текущем хранилище.
     *
     * @return Список продуктов.
     */
    public List<Food> getProducts() {
        return handler.getProducts();
    }

    /**
     * Метод проверяет необходимость добавления нового склада.
     *
     * @return Признак необходимости добавления нового склада.
     */
    public boolean isNeedMore() {
        return needMore;
    }
}