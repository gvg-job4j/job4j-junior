package ru.job4j.IO;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Valeriy Gyrievskikh
 * @since 30.01.2019
 */
public class ArchiveTest {

    private File parentDir;
    private int number;

    @Before
    public void createFileStructure() {
        String mainParent = System.getProperty("java.io.tmpdir");
        number = 5;
        parentDir = new File(mainParent + File.separator + "java_archive");
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }
        if (parentDir.exists()) {
            System.out.println(parentDir.getPath());
            for (int i = 0; i < number; i++) {
                File folder = new File(parentDir.getPath() + File.separator + Integer.toString(i));
                if (!folder.exists()) {
                    folder.mkdir();
                }
                File fileTxt = new File(folder.getPath() + File.separator + Integer.toString(i) + ".txt");
                File filePdf = new File(folder.getPath() + File.separator + Integer.toString(i) + ".pdf");
                File fileRtf = new File(folder.getPath() + File.separator + Integer.toString(i) + ".rtf");
                File fileDdd = new File(folder.getPath() + File.separator + Integer.toString(i) + ".ddd");
                File fileXml = new File(folder.getPath() + File.separator + Integer.toString(i) + ".xml");
                try {
                    fileTxt.createNewFile();
                    filePdf.createNewFile();
                    fileRtf.createNewFile();
                    fileDdd.createNewFile();
                    fileXml.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void createArchiveFromFolder() {
        String mainParent = System.getProperty("java.io.tmpdir");
        String dir = parentDir.getPath();
        String name1 = "java.xml";
        String[] params = new String[]{"-d", dir, "-e", name1, "-o", "project.zip"};
        new Archive(params);
    }
}
