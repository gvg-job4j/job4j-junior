package ru.job4j.solid.srp;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.05.2019
 */
public class ValidateCalcInput implements CalcInput {

    /**
     * Содержит источник данных.
     */
    private final CalcInput input;

    /**
     * Конструктор, инициализирует источник данных.
     *
     * @param input Используемый источник данных.
     */
    public ValidateCalcInput(CalcInput input) {
        this.input = input;
    }

    /**
     * Метод запрашивает выбор пункта меню.
     *
     * @param message Текст сообщения.
     * @return Выбранный пункт меню.
     */
    @Override
    public int ask(String message) {
        return this.input.ask(message);
    }

    /**
     * Метод запрашивает выбор пункта меню из доступного диапазона данных.
     *
     * @param message Текст сообщения.
     * @param ranges  Доступный диапазон выбора.
     * @return Выбранный пункт меню.
     */
    @Override
    public int ask(String message, int[] ranges) {
        return this.input.ask(message, ranges);
    }

    /**
     * Метод запрашивает значение операнда.
     *
     * @param message Текст сообщения.
     * @return Введенное пользователем значение.
     */
    @Override
    public double askOperand(String message) {
        return this.input.askOperand(message);
    }
}
