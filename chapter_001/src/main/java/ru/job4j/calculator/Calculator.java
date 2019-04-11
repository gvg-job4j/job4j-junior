package ru.job4j.calculator;

/**
 * Created by GVG on 11.06.2018.
 */
public class Calculator {

    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    public void div(double first, double second) {
        this.result = first / second;
    }

    public void multiply(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }
}
