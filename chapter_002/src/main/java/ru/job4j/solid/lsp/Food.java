package ru.job4j.solid.lsp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date expiredDate;
    /**
     * Дата выпуска.
     */
    private Date createDate;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.expiredDate = dateFormat.parse(expiredDate);
            this.createDate = dateFormat.parse(createDate);
            this.name = name;
            this.price = price;
            this.discount = discount;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает дату истечения срока годности.
     *
     * @return Дата истечения срока годности.
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * Метод возвращает дату выпуска.
     *
     * @return Дата выпуска.
     */
    public Date getCreateDate() {
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
