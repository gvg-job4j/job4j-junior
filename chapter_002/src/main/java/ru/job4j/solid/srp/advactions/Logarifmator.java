package ru.job4j.solid.srp.advactions;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.srp.CalcInput;
import ru.job4j.solid.srp.actions.UserAction;

/**
 * @author Valeriy Gyrievskikh
 * @since 04.06.2019
 */
public class Logarifmator implements UserAction {

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
    @Override
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Метод выполняет вычисление десятичного логарифма.
     *
     * @param input Используемый поток ввода данных.
     * @param calc  Используемый калькулятор.
     */
    @Override
    public void execute(CalcInput input, Calculator calc) {
        this.calc = calc;
        this.input = input;
        double firstOperand = getOperand(getNewOrCurrent(input));
        calc.getLog10(firstOperand);
        System.out.println(calc.getResult());
    }

    /**
     * Метод запрашивает расчетные данные у пользователя.
     *
     * @param select Вид расчетных данных.
     * @return Установленное значение.
     */
    private double getOperand(int select) {
        double value = calc.getResult();
        if (select != 2) {
            value = input.askOperand("Input value:");
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
        return String.format("%s. %s", this.key(), "Get log10.");
    }
}
