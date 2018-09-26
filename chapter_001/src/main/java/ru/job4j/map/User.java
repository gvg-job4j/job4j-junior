package ru.job4j.map;

import java.util.Calendar;

/**
 * @author Valeriy Gyrievskikh
 * модель User
 * @since 25.09.2018
 */
public class User {
    /**
     * Имя полльзователя.
     */
    private String name;
    /**
     * Количество детей.
     */
    private int children;
    /**
     * Дата рождения.
     */
    private Calendar birthday;

    /**
     * Конструктор, создает нового пользователя с заданными параметрами.
     *
     * @param name     Имя.
     * @param children Количество детей.
     * @param birthday Дата рождения.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
