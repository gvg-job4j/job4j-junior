package ru.job4j.solid.tdd;

import java.util.Map;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.08.2019
 */
public interface Template {
    String generate(String text, Map<String, String> data) throws Exception;
}
