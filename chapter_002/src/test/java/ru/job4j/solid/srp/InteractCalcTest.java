package ru.job4j.solid.srp;

import org.junit.Test;
import ru.job4j.calculator.Calculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 10.04.2019
 */
public class InteractCalcTest {

    //    private static final String LN = System.getProperty("line.separator");
    private Calculator calculator = new Calculator();

    @Test
    public void whenExitThenZero() {
        CalcInput input = new StubCalcInput(new String[]{"0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(0.0));
    }

    @Test
    public void whenAddOneAndOneThenTwo() {
        CalcInput input = new StubCalcInput(new String[]{"1", "1", "1", "1", "1", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(2.0));
    }

    @Test
    public void whenSubTenAndOneThenNine() {
        CalcInput input = new StubCalcInput(new String[]{"2", "1", "10", "1", "1", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(9.0));
    }

    @Test
    public void whenMultiplyTenOnZeroThenZero() {
        CalcInput input = new StubCalcInput(new String[]{"3", "1", "10", "1", "0", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(0.0));
    }

    @Test
    public void whenDivFiveOnTwoThenTwoAndOneHalf() {
        CalcInput input = new StubCalcInput(new String[]{"4", "1", "5", "1", "2", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(2.5));
    }

    @Test
    public void whenAddOneAndOneAndMultiplyOnTwoThenFour() {
        CalcInput input = new StubCalcInput(new String[]{"1", "1", "1", "1", "1", "3", "2", "1", "2", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(4.0));
    }

    @Test
    public void whenSubTwoAndZeroAndDivOnTwoThenOne() {
        CalcInput input = new StubCalcInput(new String[]{"2", "1", "2", "1", "0", "4", "2", "1", "2", "0"});
        InteractCalc calc = new InteractCalc(new ValidateCalcInput(input), calculator);
        calc.init();
        assertThat(calc.getResult(), is(1.0));
    }
}