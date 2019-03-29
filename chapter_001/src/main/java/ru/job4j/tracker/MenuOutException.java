package ru.job4j.tracker;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.06.2018.
 */
public class MenuOutException extends RuntimeException {

    /**
     * Метод выдает сообщение об ошибке при ошибке ввода данных пользователем.
     *
     * @param message Сообщение об ошибке.
     */
    public MenuOutException(String message) {
        super(message);
    }
}
