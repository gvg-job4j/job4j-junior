package ru.job4j.IO;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * @author Valeriy Gyrievskikh
 * @since 01.01.2019
 */
public class ByteInputTest {

    @Test
    public void whenByteArrayEvenNumberThenTrue(){
        InputStream in = new ByteArrayInputStream(new byte[]{1, 2});
        ByteInput input = new ByteInput();
        assertTrue(input.isNumber(in));
    }

    @Test
    public void whenNotByteArrayEvenNumberThenFalse(){
        InputStream in = new ByteArrayInputStream(new byte[]{1, 2, 1, 1});
        ByteInput input = new ByteInput();
        assertNotEquals(input.isNumber(in), true);
    }

}
