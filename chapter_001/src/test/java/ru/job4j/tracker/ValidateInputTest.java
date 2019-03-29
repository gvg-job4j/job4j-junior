package ru.job4j.tracker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;


/**
 * @author Valeriy Gyrievskikh.
 * @since 30.06.2018.
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidNotNumericInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[]{"invalid", "1"}));
        input.ask("Enter", new int[]{1});
        Assert.assertThat(this.mem.toString(), is(String.format("Please input numeric value...%n")));
    }

    @Test
    public void whenInvalidNumericInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[]{"28", "1"}));
        input.ask("Enter", new int[]{1});
        Assert.assertThat(this.mem.toString(), is(String.format("Please input value from menu range...%n")));
    }

    @Test
    public void whenValidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[]{"6"}));
        input.ask("Enter", new int[]{6});
        Assert.assertThat(this.mem.toString(), is(""));
    }
}
