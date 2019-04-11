package ru.job4j.solid;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 10.04.2019
 */
public class InteractCalcTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenExitThenZero() {
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(0.0));
    }

    @Test
    public void whenAddOneAndOneThenTwo() {
        String input = String.format("1%s1%s1%s1%s1%s1%s0", LN, LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(2.0));
    }

    @Test
    public void whenSubTenAndOneThenNine() {
        String input = String.format("1%s2%s1%s10%s1%s1%s0", LN, LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(9.0));
    }

    @Test
    public void whenDivFiveOnTwoThenTwoAndOneHalf() {
        String input = String.format("1%s3%s1%s5%s1%s2%s0", LN, LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(2.5));
    }

    @Test
    public void whenMultiplyTenOnZeroThenZero() {
        String input = String.format("1%s4%s1%s10%s2%s0", LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(0.0));
    }

    @Test
    public void whenAddOneAndOneAndMultiplyOnTwoThenFour() {
        String input = String.format("1%s1%s1%s1%s1%s1%s1%s1%s4%s2%s1%s2%s0",
                LN, LN, LN, LN, LN, LN, LN, LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(4.0));
    }

    @Test
    public void whenSubTwoAndZeroAndDivOnTwoThenOne() {
        String input = String.format("1%s2%s1%s2%s2%s1%s3%s2%s1%s2%s0",
                LN, LN, LN, LN, LN, LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InteractCalc calc = new InteractCalc(in);
        calc.calcStart();
        assertThat(calc.getResult(), is(1.0));
    }
}
