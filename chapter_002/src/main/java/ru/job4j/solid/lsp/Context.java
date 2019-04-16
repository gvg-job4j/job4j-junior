package ru.job4j.solid.lsp;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class Context {
    /**
     * Используемый вариант стратегии.
     */
    private TransferStrategy strategy;

    /**
     * Конструктор, устанавливает испольуему стратегию.
     *
     * @param strategy Используемая стратегия.
     */
    public Context(TransferStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Метод вызывает метод работы с едой из используемой стратегии.
     *
     * @param food Обрабатываемая еда.
     */
    public void executeStrategy(Food food) {
        strategy.addFood(food);
    }
}
