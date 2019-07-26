package ru.job4j.solid.lsp.eventloop.extended;

import ru.job4j.solid.lsp.eventloop.*;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class ExtendedControlQuality {

    /**
     * Текущий распределитель продуктов по хранилищам.
     */
    private final ExtendedFoodHandler handler = new ExtendedFoodHandler();

    /**
     * Максимальный размер склада.
     */
    private int maxSpace = 100;

    /**
     * Метод заполняет список хранилищ распределителя.
     */
    public void initHandler(List<FoodHandler> newStores) {
        handler.setStores(newStores);
    }

    /**
     * Метод устанавливает максимальный размер склада.
     *
     * @param maxSpace Новый максимальный размер склада.
     */
    public void setMaxSpace(int maxSpace) {
        this.maxSpace = maxSpace;
    }

    /**
     * Метод возвращает текущий максимальный размер склада.
     *
     * @return Текущий максимальный размер склада.
     */
    public int getMaxSpace() {
        return maxSpace;
    }

    /**
     * Метод выполняет распределение продуктов по местам хранения в зависимости от
     * срока годности продукта.
     *
     * @param food Распределяемый продукт.
     */
    public void sendToStore(Food food) {
        handler.transfer(food);
        checkWarehouseSpace();
    }

    /**
     * Метод проверяет заполненность складов, и при необходимости добавляет новый склад.
     */
    private void checkWarehouseSpace() {
        boolean needMoreWarehouses = false;
        List<FoodHandler> stores = handler.getStores();
        for (FoodHandler handler : stores) {
            if (((ExtendedFoodHandler) handler).isNeedMore()) {
                needMoreWarehouses = true;
                break;
            }
        }
        if (needMoreWarehouses) {
            stores.add(new WarehouseHandler(new Warehouse(), maxSpace));
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
