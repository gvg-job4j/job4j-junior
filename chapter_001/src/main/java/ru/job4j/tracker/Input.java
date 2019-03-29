package ru.job4j.tracker;

/**
 * @author Valeriy Gyrievskikh
 * @since 21.06.2018.
 */
public interface Input {
    String ask(String message);
    int ask(String message, int[] range);
}
