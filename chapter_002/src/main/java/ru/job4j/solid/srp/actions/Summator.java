package ru.job4j.solid.srp.actions;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.srp.CalcInput;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.05.2019
 */
public class Summator implements UserAction {

    /**
     * Используемый калькулятор.
     */
    private Calculator calc;
    /**
     * Используемый поток ввода данных.
     */
    private CalcInput input;
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
     * Метод запрашивает расчетные данные у пользователя.
     *
     * @param select Вид расчетных данных.
     * @param text   Описание запрашиваемых данных.
     * @return Установленное значение.
     */
    private double getOperand(int select, String text) {
        double value = calc.getResult();
        if (select != 2) {
            value = input.askOperand("Input " + text + " number:");
        }
        return value;
    }

    /**
     * Метод возвращает сформированное описание.
     *
     * @return Текст описания.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Add.");
    }

    /**
     * Метод выполняет сложение.
     *
     * @param input Используемый поток ввода данных.
     * @param calc  Используемый калькулятор.
     */
    @Override
    public void execute(CalcInput input, Calculator calc) {
        this.calc = calc;
        this.input = input;
        double firstOperand = getOperand(getNewOrCurrent(input), "first");
        double secondOperand = getOperand(getNewOrCurrent(input), "second");
        calc.add(firstOperand, secondOperand);
        System.out.println(calc.getResult());
    }
}
