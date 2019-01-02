package ru.job4j.IO;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Valeriy Gyrievskikh
 * @since 01.01.2019
 */
public class ByteInputTest {

    @Test
    public void whenByteArrayEvenNumberThenTrue() {
        InputStream in = new ByteArrayInputStream(new byte[]{1, 2});
        ByteInput input = new ByteInput();
        assertTrue(input.isNumber(in));
    }

    @Test
    public void whenNotByteArrayEvenNumberThenFalse() {
        InputStream in = new ByteArrayInputStream(new byte[]{1, 2, 1, 1});
        ByteInput input = new ByteInput();
        assertNotEquals(input.isNumber(in), true);
    }

    @Test
    public void whenInputHaveAbuseWorldsThenOutputDonHave() {
        String myStrObj = "Hello World Its Java!";
        String[] abuse = new String[]{"Hello", "Its"};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(myStrObj.getBytes());
        ByteInput input = new ByteInput();
        input.dropAbuses(bais, baos, abuse);
        assertThat(baos.toString(), is("World Java!"));
    }

    @Test
    public void whenInputDontHaveAbuseWorldsThenOutputAll() {
        String myStrObj = "Hello World Its Java!";
        String[] abuse = new String[]{"HelloWorld", "ItsJava"};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(myStrObj.getBytes());
        ByteInput input = new ByteInput();
        input.dropAbuses(bais, baos, abuse);
        assertThat(baos.toString(), is("Hello World Its Java!"));
    }
}