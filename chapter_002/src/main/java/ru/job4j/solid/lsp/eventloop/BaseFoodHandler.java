package ru.job4j.solid.lsp.eventloop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 05.06.2019
 */
public class BaseFoodHandler implements FoodHandler {

    /**
     * Список хранилищ.
     */
    private List<FoodHandler> stores = new ArrayList<>();

    /**
     * Метод возвращает текущий список хранилищ.
     *
     * @return Список хранилищ.
     */
    public List<FoodHandler> getStores() {
        return stores;
    }

    /**
     * Метод выполняет распределение продуктов по хранилищам.
     * Для каждого хранилища выполняется проверка свойств продукта на соответсвие требованиям хранилища.
     * Если продукт прошел проверку, он помещается в текущее хранилище, иначе выбирается следущее хранилище.
     *
     * @param food Поступивший продукт.
     * @return Результат распределения в текущее хранилище (true или false).
     */
    @Override
    public boolean transfer(Food food) {
        boolean stored = false;
        for (FoodHandler handler : stores) {
            if (handler.transfer(food)) {
                stored = true;
                break;
            }
        }
        return stored;
    }
}
