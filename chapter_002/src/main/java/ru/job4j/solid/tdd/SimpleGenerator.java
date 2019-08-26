package ru.job4j.solid.tdd;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.08.2019
 */
public class SimpleGenerator implements Template {
    /**
     * Шаблон для получения списка параметров сообщения.
     */
    private final Pattern keys = Pattern.compile("\\{.+?\\}");

    /**
     * Метод формирует строку сообщения.
     *
     * @param text Текст сообщения с параметрами.
     * @param data Список соответствий для параметров.
     * @return Готовое сообщение.
     * @throws Exception Выбрасываемое прерывание.
     */
    @Override
    public String generate(String text, Map<String, String> data) throws Exception {
        Matcher matcher = keys.matcher(text);
        Map<String, String> paramsData = new HashMap<>();
        while (matcher.find()) {
            paramsData.put(text.substring(matcher.start() + 1, matcher.end() - 1), text.substring(matcher.start() - 1, matcher.end()));
        }
        checkKeysParams(paramsData, data);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String word = paramsData.get(entry.getKey());
            while (text.contains(word)) {
                text = text.replace(word, entry.getValue());
            }
        }
        return text;
    }

    /**
     * Метод выполняет проверку полученных данных.
     *
     * @param dataParams Список параметров.
     * @param data       Список соответствий для параметров.
     * @throws Exception Выбрасываемое прерывание.
     */
    private void checkKeysParams(Map<String, String> dataParams, Map<String, String> data) throws Exception {
        List<String> emptyKeys = new ArrayList<>();
        List<String> emptyParams = new ArrayList<>();
        if (!dataParams.isEmpty() && !data.isEmpty()) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                if (!dataParams.containsKey(key)) {
                    emptyKeys.add(key);
                }
            }
            for (Map.Entry<String, String> entry : dataParams.entrySet()) {
                String key = entry.getKey();
                if (!data.containsKey(key)) {
                    emptyParams.add(key);
                }
            }
        } else if (dataParams.isEmpty()) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                emptyKeys.add(key);
            }
        } else {
            for (Map.Entry<String, String> entry : dataParams.entrySet()) {
                String key = entry.getKey();
                emptyParams.add(key);
            }
        }
        if (emptyKeys.size() > 0) {
            StringBuilder sb = new StringBuilder("No data for keys:");
            throwMyException(sb, emptyKeys);

        }
        if (emptyParams.size() > 0) {
            StringBuilder sb = new StringBuilder("No keys for params:");
            throwMyException(sb, emptyParams);
        }
    }

    /**
     * Метод выбрасывает прерывание с нужным сообщением.
     *
     * @param sb        Шаблон сообщения.
     * @param emptyData Список пустых значений.
     * @throws Exception Выбрасываемое прерывание.
     */
    private void throwMyException(StringBuilder sb, List<String> emptyData) throws Exception {
//        emptyData.stream().forEach(e -> sb.append(" " + e));
        emptyData.forEach(e -> sb.append(" ").append(e));
        throw new Exception(sb.toString());
    }
}
