package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.02.2019
 */
public class ConsoleChat {

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat();
        chat.doChat(System.in);
    }

    /**
     * Метод имитирует чат с пользователем, с записью фраз в файл.
     * На введенную фразу пользователя выводится ответ - случайная фраза из приложенного файла.
     * Если пользователь вводит слово "Стоп", вывод ответов прекращается, но чат не закрывается.
     * Если пользователь вводит слово "Продолжить", вывод ответов возобновляется.
     * Если пользователь вводит слово "Закончить", чат завершается.
     */
    public void doChat(InputStream input) {
        File fileIn = new File("in.txt");
        if (!fileIn.exists()) {
            List<String> lines = Arrays.asList("test",
                    "comp say",
                    "sadasd asdasd dfsfsdfasdas",
                    "123123",
                    "Good day!",
                    "See you later!");
            try {
                Files.write(Paths.get("in.txt"), lines, Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Scanner in = new Scanner(input);) {
            File fileOut = new File("out.txt");
            fileOut.createNewFile();
            String userSay = "";
            String compSay = "";
            boolean canSay = true;
            List<Long> answers = getComputerAnswers(fileIn.getPath());
            RandomAccessFile raf = new RandomAccessFile(fileIn.getPath(), "r");
            FileWriter fw = new FileWriter(fileOut);
            while (in.hasNextLine()) {
                userSay = in.nextLine();
                System.out.println("User say:" + userSay);
                writeStringInFile(fw, userSay, false);
                if (userSay.toLowerCase().equals("закончить")) {
                    compSay = "Chat closed. Bye!";
                    System.out.println("Comp say:" + compSay);
                    writeStringInFile(fw, compSay, true);
                    break;
                }
                canSay = checkUserWorld(userSay, canSay);
                if (canSay) {
                    raf.seek(0);
                    int index = (int) (Math.random() * answers.size() - 1);
                    raf.seek(answers.get(index));
                    raf.getFilePointer();
                    compSay = raf.readLine();
                    System.out.println("Comp say:" + compSay);
                    writeStringInFile(fw, compSay, false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выполняет запись строки в файл.
     *
     * @param fw     Объект FileWriter, выполняющий запись.
     * @param string Записываемая строка.
     * @param close  Если передано значение "истина", объект FileWriter закрывается.
     * @throws IOException Если не удалось выполнить запись.
     */
    private void writeStringInFile(FileWriter fw, String string, boolean close) throws IOException {
        fw.write(string + '\n');
        fw.flush();
        if (close) {
            fw.close();
        }
    }

    /**
     * Метод формирует список начальных индексов строк в файле, из которого берутся ответы для чата.
     *
     * @param path Путь к файлу.
     * @return Список индексов.
     * @throws IOException Если не удалось установить указатель на позицию индекса.
     */
    private List<Long> getComputerAnswers(String path) throws IOException {
        List<Long> list = new ArrayList<>();
        RandomAccessFile fileSource = new RandomAccessFile(path, "r");
        fileSource.seek(0);
        String line = "";
        while (true) {
            long index = fileSource.getFilePointer();
            line = fileSource.readLine();
            if (line == null) {
                break;
            }
            list.add(index);
        }
        return list;
    }

    /**
     * Метод проверяет наличие ключевых слов во фразе пользователя.
     *
     * @param userSay       Фраза пользователя.
     * @param currentStatus Текущее состояние чата (отвечать, не отвечать).
     * @return Новое состояние чата.
     */
    private boolean checkUserWorld(String userSay, boolean currentStatus) {
        boolean status = currentStatus;
        if (userSay.toLowerCase().equals("стоп")) {
            status = false;
        } else if (userSay.toLowerCase().equals("продолжить")) {
            status = true;
        }
        return status;
    }
}
