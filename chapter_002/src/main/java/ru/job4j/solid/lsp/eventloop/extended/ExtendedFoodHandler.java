package ru.job4j.solid.lsp.eventloop.extended;

import ru.job4j.solid.lsp.eventloop.BaseFoodHandler;
import ru.job4j.solid.lsp.eventloop.Food;
import ru.job4j.solid.lsp.eventloop.FoodHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 05.06.2019
 */
public class ExtendedFoodHandler extends BaseFoodHandler {

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
     * Метод устанавливает список хранилищ.
     *
     * @param stores Полученный список хранилищ.
     */
    public void setStores(List<FoodHandler> stores) {
        this.stores = stores;
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

    /**
     * Метод проверяет необходимость добавления нового склада.
     *
     * @return Признак необходимости добавления нового склада.
     */
    public boolean isNeedMore() {
        return false;
    }
}
