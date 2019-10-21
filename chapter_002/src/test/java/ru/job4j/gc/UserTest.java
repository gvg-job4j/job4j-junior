package ru.job4j.gc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 16.10.2019
 */
public class UserTest {
    @Test
    public void whenCreateMultiplyObjects() {
        for (int i = 0; i < 1000; i++) {
            User user = new User("Test");
        }
        assertTrue(true);
    }

}