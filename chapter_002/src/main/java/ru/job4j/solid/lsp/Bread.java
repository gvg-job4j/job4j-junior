package ru.job4j.solid.lsp;

import java.util.Date;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class Bread extends Food {
    /**
     * Коструктор, устанавливает параметры объекта.
     *
     * @param name        Наименование.
     * @param expiredDate Дата истечения срока годности.
     * @param createDate  Дата выпуска.
     * @param price       Цена.
     * @param discount    Скидка.
     */
    public Bread(String name, String expiredDate, String createDate, double price, int discount) {
        super(name, expiredDate, createDate, price, discount);
    }
}
