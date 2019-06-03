package ru.job4j.solid.srp;

/**
 * @author Valeriy Gyrievskikh
 * @since 22.05.2019
 */
public interface CalcInput {
    /**
     * Метод запрашивает ввод информации у пользователя.
     *
     * @param message Сообщение пользователю с описанием ожидаемой информации.
     * @return Полученные данные от пользователя.
     */
    int ask(String message);

    /**
     * Метод проверяет корректность ввода данных пользователем.
     *
     * @param message Сообщение пользователю.
     * @param range   Список доступных значений.
     * @return Введеное значение.
     */
    int ask(String message, int[] range);

    /**
     * Метод запрашивает ввод данных для выполнения расчетов.
     *
     * @param message Текст сообщения.
     * @return Указанное значение.
     */
    double askOperand(String message);

}
