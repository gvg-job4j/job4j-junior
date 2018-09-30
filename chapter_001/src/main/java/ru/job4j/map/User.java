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

//    /**
//     * Метод возврашает хеш-код объекта, рассчитанный по полям.
//     *
//     * @return Хеш-код.
//     */
//    @Override
//    public int hashCode() {
//        return this.name.hashCode() + this.birthday.hashCode() + children;
//    }

    /**
     * Метод выполняет сравнение объектов по значениям полей.
     *
     * @param user Объект, с которым выполняется сравнение.
     * @return Результат сравнения (true, если значения полей совпадают, или false).
     */
    @Override
    public boolean equals(Object user) {
        User compared = ((User) user);
        return (this.name.equals(compared.name) && this.birthday.equals(compared.birthday) && this.children == compared.children);
    }
}
