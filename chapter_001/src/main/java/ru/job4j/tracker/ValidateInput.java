package ru.job4j.tracker;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.06.2018.
 */
public class ValidateInput implements Input {

    /**
     * Содержит источник данных.
     */
    private final Input input;

    /**
     * Конструктор, инициализирует источник данных.
     *
     * @param input Используемый источник данных.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Метод запрашивает ввод информации у пользователя.
     *
     * @param message Сообщение пользователю с описанием ожидаемой информации.
     * @return Полученные данные от пользователя.
     */
    @Override
    public String ask(String message) {
        return this.input.ask(message);
    }

    /**
     * Метод проверяет корректность ввода данных пользователем.
     *
     * @param message Сообщение пользователю.
     * @param range   Список доступных значений.
     * @return Введеное значение.
     */
    @Override
    public int ask(String message, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(message, range);
                invalid = valueInRange(value, range);
            } catch (MenuOutException e) {
                System.out.println("Please input value from menu range...");
            } catch (NumberFormatException e) {
                System.out.println("Please input numeric value...");
            }
        } while (invalid);
        return value;
    }

    private boolean valueInRange(int key, int[] range) {
        boolean notInRange = true;
        for (int value : range
                ) {
            if (value == key) {
                notInRange = false;
                break;
            }
        }
        if (notInRange) {
            throw new MenuOutException("Out of menu range!");
        }
        return notInRange;
    }
}
