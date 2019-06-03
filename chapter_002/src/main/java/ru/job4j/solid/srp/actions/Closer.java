package ru.job4j.solid.srp.actions;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.srp.CalcInput;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.05.2019
 */
public class Closer implements UserAction {
    /**
     * Идентификатор вида расчета.
     */
    private int key;

    /**
     * Метод возвращает идентификатор вида расчета.
     *
     * @return Идентификатор вида расчета.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Метод устанавливает идентификатор вида расчета.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Метод возвращает сформированное описание.
     *
     * @return Текст описания.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Exit.");
    }

    /**
     * Метод выполняет выход из программы.
     *
     * @param input Используемый поток ввода данных.
     * @param calc  Используемый калькулятор.
     */
    @Override
    public void execute(CalcInput input, Calculator calc) {
        System.out.println("Выход из программы...");
    }
}
