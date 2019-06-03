package ru.job4j.solid.srp;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.srp.actions.*;

import java.util.ArrayList;

/**
 * Класс реализует интерактивный калькулятор.
 *
 * @author Valeriy Gyrievskikh
 * @since 22.05.2019
 */
public class InteractCalc {

    /**
     * Калькулятор, используемый для вычислений.
     */
    private final Calculator calculator;

    /**
     * Устройство ввода данных.
     */
    private final CalcInput input;

    /**
     * Действия пользователя.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор, устанавливает поток ввода и калькулятор.
     *
     * @param input      Поток ввода.
     * @param calculator Калькулятор.
     */
    public InteractCalc(CalcInput input, Calculator calculator) {
        this.calculator = calculator;
        this.input = input;
    }

    /**
     * Метод показывает меню запуска и обрабатывает выбор пользователя.
     */
    public void init() {
        int[] ranges = fillActions();
        boolean exit = false;
        do {
            show();
            int key = this.input.ask("Select: ", ranges);
            if (key == ranges[ranges.length - 1]) {
                exit = true;
            }
            select(key);
        } while (!exit);
    }

    /**
     * Метод запускает выбранную процедуру.
     *
     * @param key Выбранный пункт меню.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.calculator);
    }

    /**
     * Метод выводит меню программы.
     */
    public void show() {
        for (UserAction action : this.actions
                ) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Метод формирует список доступных операций.
     *
     * @return Список доступных операций.
     */
    public int[] fillActions() {
        actions.add(new Summator());
        actions.add(new Subtractor());
        actions.add(new Multiplator());
        actions.add(new Divider());
        actions.add(new Closer());
        this.actions.trimToSize();
        int[] keys = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) != null) {
                UserAction action = actions.get(i);
                action.setKey(i);
                keys[i] = action.key();
            }
        }
        return keys;
    }

    /**
     * Метод возвращает текущий результат вычислений.
     *
     * @return Текущий результат.
     */
    public double getResult() {
        return calculator.getResult();
    }

    /**
     * Запускт программы.
     *
     * @param args Входные параметры.
     */
    public static void main(String[] args) {
        new InteractCalc(new ValidateCalcInput(new UserInput()), new Calculator()).init();

    }
}
