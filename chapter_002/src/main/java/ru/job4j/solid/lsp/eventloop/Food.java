package ru.job4j.solid.lsp.eventloop;

import java.time.LocalDate;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class Food {
    /**
     * Наименование.
     */
    private String name;
    /**
     * Дата истечения срока годности.
     */
    private LocalDate expiredDate;
    /**
     * Дата выпуска.
     */
    private LocalDate createDate;
    /**
     * Цена.
     */
    private double price;
    /**
     * Скидка.
     */
    private int discount;

    /**
     * Коструктор, устанавливает параметры объекта.
     *
     * @param name        Наименование.
     * @param expiredDate Дата истечения срока годности.
     * @param createDate  Дата выпуска.
     * @param price       Цена.
     * @param discount    Скидка.
     */
    public Food(String name, String expiredDate, String createDate, double price, int discount) {
        this.expiredDate = LocalDate.parse(expiredDate);
        this.createDate = LocalDate.parse(createDate);
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    /**
     * Метод возвращает дату истечения срока годности.
     *
     * @return Дата истечения срока годности.
     */
    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    /**
     * Метод возвращает дату выпуска.
     *
     * @return Дата выпуска.
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Метод возвращает цену продукта.
     *
     * @return Цена.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Метод устанавливает цену на продукт.
     *
     * @return Новая цена.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Метод возвращает скидку на продукт.
     *
     * @return Скидка.
     */
    public int getDiscount() {
        return discount;
    }

}
