package ru.job4j.calculator;

/**
 * Created by GVG on 11.06.2018.
 */
public class Calculator {

    /**
     * Результат вычислений.
     */
    private double result;

    /**
     * Метод вычисляет сумму переданных значений.
     *
     * @param first  Первое значение.
     * @param second Второе значение.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Метод вычисляет разность переданных значений.
     *
     * @param first  Первое значение.
     * @param second Второе значение.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Метод вычисляет результат деления переданных значений.
     *
     * @param first  Первое значение.
     * @param second Второе значение.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Метод вычисляет результат умножения переданных значений.
     *
     * @param first  Первое значение.
     * @param second Второе значение.
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Метод вычисляет синус угла.
     *
     * @param angle Значение вычисляемого угла (в градусах).
     */
    public void getSin(double angle) {
        this.result = Math.sin(Math.toRadians(angle));
    }

    /**
     * Метод вычисляет косинус угла.
     *
     * @param angle Значение вычисляемого угла (в градусах).
     */
    public void getCos(double angle) {
        this.result = Math.cos(Math.toRadians(angle));
    }

    /**
     * Метод вычисляет тангенс угла.
     *
     * @param angle Значение вычисляемого угла (в градусах).
     */
    public void getTan(double angle) {
        this.result = Math.tan(Math.toRadians(angle));
    }

    /**
     * Метод вычисляет котангенс угла.
     *
     * @param angle Значение вычисляемого угла (в градусах).
     */
    public void getCotan(double angle) {
        this.result = 1.0 / (Math.tan(Math.toRadians(angle)));
    }

    /**
     * Метод вычисляет натуральный логарифм переданного значения.
     *
     * @param value Переданное значение.
     */
    public void getLogn(double value) {
        this.result = Math.log(value);
    }

    /**
     * Метод вычисляет десятичный логарифм переданного значения.
     *
     * @param value Переданное значение.
     */
    public void getLog10(double value) {
        this.result = Math.log10(value);
    }

    public double getResult() {
        return this.result;
    }
}
