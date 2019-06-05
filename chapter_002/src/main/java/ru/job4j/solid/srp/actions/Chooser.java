package ru.job4j.solid.srp.actions;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.srp.CalcInput;
import ru.job4j.solid.srp.InteractCalc;

/**
 * @author Valeriy Gyrievskikh
 * @since 05.06.2019
 */
public class Chooser implements UserAction {
    /**
     * Текущая форма.
     */
    private final InteractCalc shell;

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
        return key;
    }

    /**
     * Метод устанавливает идентификатор вида расчета.
     */
    @Override
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Метод выполняет выбор варианта калькулятора.
     *
     * @param input Используемый поток ввода данных.
     * @param calc  Используемый калькулятор.
     */
    @Override
    public void execute(CalcInput input, Calculator calc) {
        String message = "Available calc types:" + "\n"
                + "1 - standart;" + "\n"
                + "2 - engeneering;" + "\n"
                + "3 - all functions." + "\n"
                + "Select calc type:";
        shell.fillActions(input.ask(message, new int[]{1, 2, 3}));
    }

    /**
     * Конструктор, устаналвливает используемую форму калькулятора.
     *
     * @param shell Используемая форма.
     */
    public Chooser(InteractCalc shell) {
        this.shell = shell;
    }

    /**
     * Метод возвращает сформированное описание.
     *
     * @return Текст описания.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Select calculator type.");
    }
}
