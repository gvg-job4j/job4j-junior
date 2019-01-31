package ru.job4j.IO;

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
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }
}
