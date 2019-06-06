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
     * Используемый склад.
     */
    private Warehouse warehouse = new Warehouse();
    /**
     * Используемый магазин.
     */
    private Shop shop = new Shop();
    /**
     * Используемая мусорка.
     */
    private Thrash thrash = new Thrash();

    /**
     * Метод выполняет распределение продуктов по местам хранения в зависимости от
     * срока годности продукта.
     *
     * @param food Распределяемый продукт.
     */
    public void sendToStore(Food food) {
        handler.setNext(warehouse);
        if (!handler.transfer(food)) {
            handler.setNext(shop);
            if (!handler.transfer(food)) {
                handler.setNext(thrash);
                handler.transfer(food);
            }
        }
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