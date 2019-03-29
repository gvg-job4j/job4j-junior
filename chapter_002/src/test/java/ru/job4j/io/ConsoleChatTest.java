package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.02.2019
 */
public class ConsoleChatTest {

    @Test
    public void doConsoleChat() {
        File fileIn = new File("testInput.txt");
        if (!fileIn.exists()) {
            List<String> lines = Arrays.asList("test",
                    "Стоп",
                    "Привет",
                    "Продолжить",
                    "Good day!",
                    "Закончить");
            try {
                Files.write(Paths.get("testInput.txt"), lines, Charset.forName("UTF-8"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileIn);
            ConsoleChat chat = new ConsoleChat();
            chat.doChat(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
