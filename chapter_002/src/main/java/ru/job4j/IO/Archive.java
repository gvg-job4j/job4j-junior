package ru.job4j.IO;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.01.2019
 */
public class Archive {
    /**
     * Путь к папке, которую архивируем.
     */
    private String directory;
    /**
     * Список расширений файлов, которые нужно архивировать.
     */
    private ArrayList<String> exts = new ArrayList<>();
    /**
     * Название архива.
     */
    private String output;

    /**
     * Конструктор, инициализирует переменные, запускает архивирование.
     *
     * @param args Список входных параметров.
     */
    public Archive(String... args) {
        int dir = -1;
        int out = -1;
        int ex = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                dir = i;
            }
            if (args[i].equals("-e")) {
                ex = i;
            }
            if (args[i].equals("-o")) {
                out = i;
            }
        }
        if (dir != -1) {
            directory(args[dir + 1]);
        }
        if (out != -1) {
            output(args[out + 1]);
        }
        if (ex != -1) {
            exclude(Arrays.copyOfRange(args, ex + 1, out));
        }
        createArchive();
    }

    /**
     * Метод устанавливает путь к архивируемой папке.
     *
     * @param directory Путь к папке.
     */
    private void directory(String directory) {
        this.directory = directory;
    }

    /**
     * Метод заполняет список расширений.
     *
     * @param array Маасив строк, из которых нужно получить расширения.
     */
    private void exclude(String[] array) {
        for (int i = 0; i < array.length; i++) {
            String ext = array[i].substring(array[i].lastIndexOf('.') + 1);
            exts.add(ext);
        }
    }

    /**
     * Метод устанавливает название архива.
     *
     * @param output Название архива.
     */
    private void output(String output) {
        this.output = output;
    }

    /**
     * Метод выполняет архивирование файлов с указанными расширениями в указанной папке.
     */
    public void createArchive() {
        if (directory.isEmpty() || output.isEmpty() || exts.size() == 0) {
            System.out.println("Not enough data to create an archive!");
            return;
        }
        try (ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(directory + File.separator + output));
        ) {
            File parentDir = new File(directory);
            if (parentDir.exists() && parentDir.isDirectory()) {
                Queue<File> dirList = new LinkedList<>();
                dirList.offer(parentDir);
                while (!dirList.isEmpty()) {
                    File file = dirList.poll();
                    File[] filesInDir = file.listFiles();
                    for (int i = 0; i < filesInDir.length; i++) {
                        if (filesInDir[i].getName().equals(output)) {
                            continue;
                        }
                        String entryName = filesInDir[i].getPath().substring(directory.length() + 1);
                        if (filesInDir[i].isDirectory()) {
                            zipStream.putNextEntry(new ZipEntry(entryName + File.separator));
                            dirList.offer(filesInDir[i]);
                        } else {
                            int pointIndex = filesInDir[i].getName().lastIndexOf('.');
                            if (pointIndex != -1) {
                                String ext = filesInDir[i].getName().substring(pointIndex + 1);
                                if (exts.stream().anyMatch((s) -> s.equals(ext))) {
                                    zipStream.putNextEntry(new ZipEntry(entryName));
                                    FileInputStream fis = new FileInputStream(filesInDir[i]);
                                    byte[] buffer = new byte[fis.available()];
                                    fis.read(buffer);
                                    zipStream.write(buffer);
                                }
                            }
                        }
                        zipStream.closeEntry();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
