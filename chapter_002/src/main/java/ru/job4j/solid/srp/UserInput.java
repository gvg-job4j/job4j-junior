package ru.job4j.solid.srp;

import java.util.Scanner;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.05.2019
 */
public class UserInput implements CalcInput {

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
    public int ask(String message) {
        System.out.println(message);
        return scanner.nextInt();
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
        int key = this.ask(message);
        boolean exist = false;
        do {
            for (int i = 0; i < range.length; i++) {
                if (range[i] == key) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                System.out.println("Please input value from menu range...");
            }
        } while (!exist);
        return key;
    }

    /**
     * Метод запрашивает ввод данных для выполнения расчетов.
     *
     * @param message Текст сообщения.
     * @return Указанное значение.
     */
    @Override
    public double askOperand(String message) {
        System.out.println(message);
        return scanner.nextDouble();
    }
}
