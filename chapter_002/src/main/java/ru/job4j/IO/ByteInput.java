package ru.job4j.IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Valeriy Gyrievskikh
 * @since 01.01.2019
 */
public class ByteInput {
    /**
     * Метод проверяет, что в байтовом потоке записано четное число.
     * @param in Входной байтовый поток.
     * @return Результат проверки.
     */
    boolean isNumber(InputStream in) {
        boolean isEven = false;
        try (InputStream input = in) {
            StringBuilder bytes = new StringBuilder();
            int a = 0;
            while ((a = input.read()) != -1) {
                System.out.println(a);
                bytes.append(a);
            }
            if (Integer.parseInt( bytes.toString()) % 2 == 0) {
                isEven = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isEven;
    }
}
