package ru.job4j.solid.lsp;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public interface TransferStrategy {
    /**
     * Метод добавляет выбранную еду в хранилище.
     *
     * @param food Выбранная еда.
     */
    public abstract void addFood(Food food);
}
