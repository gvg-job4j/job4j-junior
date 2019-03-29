package ru.job4j.tracker;

/**
 * @author Valeriy Gyrievskikh
 * @since 24.06.2018.
 */
public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
