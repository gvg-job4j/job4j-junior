package ru.job4j.solid.srp;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.05.2019
 */
public class StubCalcInput implements CalcInput {

    /**
     * Это поле содержит последовательность ответов пользователя.
     */
    private final String[] value;

    /**
     * Поле считает количество вызовом метода ask.
     * При каждом вызове надо передвинуть указатель на новое число.
     */
    private int position;

    /**
     * Конструктор, инициализирует список команд пользователя.
     *
     * @param value Список команд пользователя.
     */
    public StubCalcInput(final String[] value) {
        this.value = value;
    }

    /**
     * Метод возвращает следующий элемент последовательности ответов.
     *
     * @param message Сообщение пользователю.
     * @return Элемент последовательности ответов.
     */
    @Override
    public int ask(String message) {
        return Integer.parseInt(this.value[this.position++]);
    }

    /**
     * Метод возвращает следующий элемент последовательности ответов.
     *
     * @param message Сообщение пользователю.
     * @param range   Список доступных значений.
     * @return Элемент последовательности ответов.
     */
    @Override
    public int ask(String message, int[] range) {
        return Integer.parseInt(this.value[this.position++]);
    }

    /**
     * Метод возвращает следующий элемент последовательности ответов.
     *
     * @param message Сообщение пользователю.
     * @return Элемент последовательности ответов.
     */
    @Override
    public double askOperand(String message) {
        return Double.parseDouble(this.value[this.position++]);
    }
}
