package ru.job4j.solid.srp.actions;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.srp.CalcInput;

/**
 * @author Valeriy Gyrievskikh
 * @since 22.05.2019
 */
public interface UserAction {

    int key();

    void setKey(int key);

    void execute(CalcInput input, Calculator calc);

    String info();

    default int getNewOrCurrent(CalcInput input) {
        int value = 0;
        do {
            String message = "Set first value. 1 - new, 2 - current result";
            value = input.ask(message);
            if (value == 1 || value == 2) {
                break;
            }
        } while (true);
        return value;
    }
}
