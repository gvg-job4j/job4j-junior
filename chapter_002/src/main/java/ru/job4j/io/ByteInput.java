package ru.job4j.io;

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
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            Stream<String> abuseStream = Arrays.stream(abuse);
            String line;
            while ((line = br.readLine()) != null) {
                final String[] goodLine = {line};
                abuseStream.forEach(s -> goodLine[0] = goodLine[0].replace(s, "").trim().replace("  ", " "));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                bw.write(goodLine[0]);
                bw.flush();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
