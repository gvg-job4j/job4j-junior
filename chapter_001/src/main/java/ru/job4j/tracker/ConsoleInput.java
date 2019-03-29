package ru.job4j.tracker;

import java.util.Scanner;

/**
 * @author Valeriy Gyrievskikh
 * @since 20.06.2018.
 */
public class ConsoleInput implements Input {

    /**
     * Устройство ввода информации.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод запрашивает ввод информации у пользователя.
     *
     * @param message Сообщение пользователю с описанием ожидаемой информации.
     * @return Полученные данные от пользователя.
     */
    @Override
    public String ask(String message) {
        System.out.println(message);
        return scanner.nextLine();
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
        int key = Integer.valueOf(this.ask(message));
        boolean exist = false;
        for (int i = 0; i < range.length; i++) {
            if (range[i] == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Please input value from menu range...");
        }
        return key;
    }
}
