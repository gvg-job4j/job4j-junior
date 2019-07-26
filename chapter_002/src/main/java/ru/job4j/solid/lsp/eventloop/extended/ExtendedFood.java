package ru.job4j.solid.lsp.eventloop.extended;

import ru.job4j.solid.lsp.eventloop.Food;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.07.2019
 */
public class ExtendedFood extends Food {

    /**
     * Признак овоща.
     */
    private boolean isVegetable;
    /**
     * Признак возможности переработки.
     */
    private boolean canReproduct;

    /**
     * Коструктор, устанавливает параметры объекта.
     *
     * @param name        Наименование.
     * @param expiredDate Дата истечения срока годности.
     * @param createDate  Дата выпуска.
     * @param price       Цена.
     * @param discount    Скидка.
     */
    public ExtendedFood(String name, String expiredDate, String createDate, double price, int discount) {
        this(name, expiredDate, createDate, price, discount, false, false);
    }

    /**
     * Коструктор, устанавливает параметры объекта, включая признаки овоща и переработки.
     *
     * @param name         Наименование.
     * @param expiredDate  Дата истечения срока годности.
     * @param createDate   Дата выпуска.
     * @param price        Цена.
     * @param discount     Скидка.
     * @param isVegetable  Это овощ.
     * @param canReproduct Продукт можно переработать.
     */
    public ExtendedFood(String name, String expiredDate, String createDate, double price, int discount,
                        boolean isVegetable, boolean canReproduct) {
        super(name, expiredDate, createDate, price, discount);
        this.isVegetable = isVegetable;
        this.canReproduct = canReproduct;
    }

    /**
     * Метод возвращает признак овоща.
     *
     * @return Признак овоща.
     */
    public boolean isVegetable() {
        return isVegetable;
    }

    /**
     * Метод возвращает признак возможности переработки.
     *
     * @return Признак возможности переработки.
     */
    public boolean isCanReproduct() {
        return canReproduct;
    }
}
