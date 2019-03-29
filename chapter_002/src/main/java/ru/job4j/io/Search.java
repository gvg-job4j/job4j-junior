package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.01.2019
 */
public class Search {
    /**
     * Метод возвращает список всех файлов с указанными расширениями.
     *
     * @param parent Наименование папки, в которой выполняется поиск.
     * @param exts   Список расширений файлов.
     * @return Список файлов.
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> files = new ArrayList<>();
        File parentDir = new File(parent);
        if (parentDir.exists() && parentDir.isDirectory()) {
            Queue<File> dirList = new LinkedList<>();
            dirList.offer(parentDir);
            while (!dirList.isEmpty()) {
                File parentFile = dirList.poll();
                for (File file : parentFile.listFiles()) {
                    if (file.isDirectory()) {
                        dirList.offer(file);
                    } else {
                        checkExtensions(file, files, exts);
                    }
                }
            }
        }
        return files;
    }

    /**
     * Метод выполняет добавление файлов, подходящих по расширению, в список.
     *
     * @param file  Добавляемый файл.
     * @param files Список файлов.
     * @param exts  Список разрешенных расширений.
     */
    private void checkExtensions(File file, List<File> files, List<String> exts) {
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (exts.stream().anyMatch(s -> s.equals(ext))) {
            files.add(file);
        }
    }
}
