package ru.job4j.solid.srp;

import org.junit.Test;
import ru.job4j.calculator.Calculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.06.2019
 */
public class AdvancedInteractCalcTest {

    private Calculator calculator = new Calculator();

    @Test
    public void whenGetSin90Then1() {
        CalcInput input = new StubCalcInput(new String[]{"5", "1", "90", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertTrue(Math.abs(calc.getResult() - 1.0) < 0.1);
    }

    @Test
    public void whenGetCos90Then0() {
        CalcInput input = new StubCalcInput(new String[]{"6", "1", "90", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertTrue(Math.abs(calc.getResult() - 0.0) < 0.1);
    }

    @Test
    public void whenGetTan45Then1() {
        CalcInput input = new StubCalcInput(new String[]{"7", "1", "45", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertTrue(Math.abs(calc.getResult() - 1.0) < 0.1);
    }

    @Test
    public void whenGetCtn45Then1() {
        CalcInput input = new StubCalcInput(new String[]{"8", "1", "45", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertTrue(Math.abs(calc.getResult() - 1.0) < 0.1);
    }

    @Test
    public void whenGetLog10From10Then1() {
        CalcInput input = new StubCalcInput(new String[]{"9", "1", "10", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(1.0));
    }

    @Test
    public void whenGetLogNThen1() {
        CalcInput input = new StubCalcInput(new String[]{"10", "1", "3", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertTrue(Math.abs(calc.getResult() - 1.0) < 0.1);
    }
}
