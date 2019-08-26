package ru.job4j.solid.tdd;

import static org.hamcrest.core.Is.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.08.2019
 */
public class TemplateTest {

    @Test
    public void whenTextHaveDataThenGenerateStringWithParams() throws Exception {
        String expects = "Hello! Hello, World! Hello, World!";
        String text = "${hello}! Hello, World! ${hello}, ${name}!";
        Map<String, String> params = new HashMap<>();
        params.put("name", "World");
        params.put("hello", "Hello");
        SimpleGenerator gen = new SimpleGenerator();
        String result = gen.generate(text, params);
        assertThat(result, is(expects));
    }

    @Test
    public void whenHaveUnnecessaryParamThenException() throws Exception {
        String text = "${hello}, ${name}!";
        Map<String, String> params = new HashMap<>();
        params.put("name", "World");
        params.put("hello", "Hello");
        params.put("param", "Param");
        SimpleGenerator gen = new SimpleGenerator();
        try {
            String result = gen.generate(text, params);
            Assert.fail("Expected Exception");
        } catch (Exception thrown) {
            System.out.println(thrown.getMessage());
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenNoEnafParamsThenException() throws Exception {
        String text = "${hello}, ${name}!";
        Map<String, String> params = new HashMap<>();
        params.put("name", "World");
        SimpleGenerator gen = new SimpleGenerator();
        try {
            String result = gen.generate(text, params);
            Assert.fail("Expected Exception");
        } catch (Exception thrown) {
            System.out.println(thrown.getMessage());
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenHaveNoParamsThenException() throws Exception {
        String text = "${hello}, ${name}!";
        Map<String, String> params = new HashMap<>();
        SimpleGenerator gen = new SimpleGenerator();
        try {
            String result = gen.generate(text, params);
            Assert.fail("Expected Exception");
        } catch (Exception thrown) {
            System.out.println(thrown.getMessage());
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenHaveNoKeysThenException() throws Exception {
        String text = "Hello, World!";
        Map<String, String> params = new HashMap<>();
        params.put("name", "World");
        params.put("hello", "Hello");
        params.put("param", "Param");
        SimpleGenerator gen = new SimpleGenerator();
        try {
            String result = gen.generate(text, params);
            Assert.fail("Expected Exception");
        } catch (Exception thrown) {
            System.out.println(thrown.getMessage());
            assertNotEquals("", thrown.getMessage());
        }
    }
}