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
                File file = dirList.poll();
                File[] filesInDir = file.listFiles();
                if (filesInDir != null) {
                    for (int i = 0; i < filesInDir.length; i++) {
                        if (filesInDir[i].exists()) {
                            if (filesInDir[i].isDirectory()) {
                                dirList.offer(filesInDir[i]);
                            } else {
                                int pointIndex = filesInDir[i].getName().lastIndexOf('.');
                                if (pointIndex != -1) {
                                    String ext = filesInDir[i].getName().substring(pointIndex + 1);
                                    if (exts.stream().anyMatch((s) -> s.equals(ext)) && !files.contains(filesInDir[i])) {
                                        files.add(filesInDir[i]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return files;
    }
}
