package ru.job4j.IO;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.01.2019
 */
public class Archive {

    private String directory;
    private String exts;
    private String output;

    public Archive(String ... args){

    }
    /**
     * Метод выполняет архивирование файлов с указанными расширениями в указанной папке.
     *
     * @param path Папка, содержимое которой нужно архивировать.
     * @param exts Список расширений файлов для архивации.
     */
    public void createArchive(String path, List<String> exts) {
        try (ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(path + "\\output.zip"));
        ) {
            File parentDir = new File(path);
            if (parentDir.exists() && parentDir.isDirectory()) {
                Queue<File> dirList = new LinkedList<>();
                dirList.offer(parentDir);
                while (!dirList.isEmpty()) {
                    File file = dirList.poll();
                    File[] filesInDir = file.listFiles();
                    if (filesInDir != null) {
                        for (int i = 0; i < filesInDir.length; i++) {
                            if (filesInDir[i].getName().equals("output.zip")) {
                                continue;
                            }
                            if (filesInDir[i].exists()) {
                                String entryName = filesInDir[i].getPath().substring(path.length() + 1);
                                if (filesInDir[i].isDirectory()) {
                                    ZipEntry entry = new ZipEntry(entryName + "\\");
                                    zipStream.putNextEntry(entry);
                                    dirList.offer(filesInDir[i]);
                                } else {
                                    int pointIndex = filesInDir[i].getName().lastIndexOf('.');
                                    if (pointIndex != -1) {
                                        String ext = filesInDir[i].getName().substring(pointIndex + 1);
                                        if (exts.stream().anyMatch((s) -> s.equals(ext))) {
                                            ZipEntry entry = new ZipEntry(entryName);
                                            zipStream.putNextEntry(entry);
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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
