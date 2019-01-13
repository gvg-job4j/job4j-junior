package ru.job4j.IO;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Valeriy Gyrievskikh
 * @since 01.01.2019
 */
public class ByteInput implements Sort {
    /**
     * Метод проверяет, что в байтовом потоке записано четное число.
     *
     * @param in Входной байтовый поток.
     * @return Результат проверки.
     */
    boolean isNumber(InputStream in) {
        boolean isEven = false;
        try (InputStream input = in) {
            StringBuilder bytes = new StringBuilder();
            int a = 0;
            while ((a = input.read()) != -1) {
                bytes.append(a);
            }
            if (Integer.parseInt(bytes.toString()) % 2 == 0) {
                isEven = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isEven;
    }

    /**
     * Метод записывает в выходной поток только те слова, которых нет в переданном массиве.
     *
     * @param in    Входной поток со списком слов.
     * @param out   Выходной поток.
     * @param abuse Массив с запрещенными словами.
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (InputStream input = in
        ) {
            StringBuilder bytes = new StringBuilder();
            int a = 0;
            while ((a = input.read()) != -1) {
                if (a == 32) {
                    boolean isAbuse = false;
                    for (int i = 0; i < abuse.length; i++) {
                        if (abuse[i].equals(bytes.toString())) {
                            isAbuse = true;
                            break;
                        }
                    }
                    if (!isAbuse) {
                        bytes.appendCodePoint(a);
                        out.write(bytes.toString().getBytes());
                        out.flush();
                    }
                    bytes = new StringBuilder();
                } else {
                    bytes.appendCodePoint(a);
                }
            }
            if (bytes.length() > 0) {
                out.write(bytes.toString().getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает текстовый файл, отсортированный по возрастанию длин строк.
     *
     * @param sourse   Исходный файл.
     * @param distance Отсортированный файл.
     */
    @Override
    public void sort(File sourse, File distance) {
        try {
            if (sourse.exists()) {
                RandomAccessFile fileSourse = new RandomAccessFile(sourse.getPath(), "r");
                RandomAccessFile fileDest = new RandomAccessFile(distance.getPath(), "rw");
                fileSourse.seek(0);
                String line = "";
                Map<Long, Integer> lines = new HashMap<>();
                while (true) {
                    long index = fileSourse.getFilePointer();
                    line = fileSourse.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.put(index, line.length());
                }
                Map<Long, Integer> result = new LinkedHashMap<>();
                Stream<Map.Entry<Long, Integer>> st = lines.entrySet().stream();
                st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
                fileSourse.seek(0);
                for (Map.Entry<Long, Integer> entry : result.entrySet()) {
                    fileSourse.seek(entry.getKey());
                    line = fileSourse.readLine();
                    System.out.println(line);
                    fileDest.writeBytes(line + "\n");
                    fileSourse.seek(0);
                }
                fileDest.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
