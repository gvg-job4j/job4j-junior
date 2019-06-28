package ru.job4j.solid.lsp.eventloop;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class ControlQuality {

    /**
     * Текущий распределитель продуктов по хранилищам.
     */
    private final FoodHandler handler = new BaseFoodHandler();

    /**
     * Метод заполняет список хранилищ распределителя.
     */
    public void initHandler() {
        List<FoodHandler> stores = handler.getStores();
        stores.clear();
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Thrash());
    }

    /**
     * Метод выполняет распределение продуктов по местам хранения в зависимости от
     * срока годности продукта.
     *
     * @param food Распределяемый продукт.
     */
    public void sendToStore(Food food) {
        handler.transfer(food);
    }

    /**
     * Метод передает список используемых хранилищ.
     *
     * @return Список используемых хранилищ.
     */
    public List<FoodHandler> getStores() {
        return handler.getStores();
    }
}
