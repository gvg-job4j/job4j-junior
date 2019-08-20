package ru.job4j.solid.lsp.eventloop;

import java.util.*;

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

    /**
     * Метод выполняет перераспределение продуктов.
     */
    public void resort() {
        List<Food> foodList = new ArrayList<>();
        List<FoodHandler> stores = handler.getStores();
        for (FoodHandler store : stores) {
            if (store instanceof Shop) {
                foodList.addAll(((Shop) store).getProducts());

            }
            if (store instanceof Thrash) {
                foodList.addAll(((Thrash) store).getProducts());
            }
            if (store instanceof Warehouse) {
                foodList.addAll(((Warehouse) store).getProducts());
            }
        }
        initHandler();
        for (Food food : foodList) {
            sendToStore(food);
        }
    }
}
