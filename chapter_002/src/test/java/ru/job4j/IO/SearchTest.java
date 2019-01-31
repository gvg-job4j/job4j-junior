package ru.job4j.IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.01.2019
 */
public class SearchTest {

    @Test
    public void getFileListFromParent() {
        String mainParent = System.getProperty("java.io.tmpdir");
        File parentDir = new File(mainParent + "\\java_search");
        int number = 5;
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }
        if (parentDir.exists()) {
            System.out.println(parentDir.getPath());
            for (int i = 0; i < number; i++) {
                File folder = new File(parentDir.getPath() + "\\" + Integer.toString(i));
                if (!folder.exists()) {
                    folder.mkdir();
                }
                File fileTxt = new File(folder.getPath() + "\\" + Integer.toString(i) + ".txt");
                File filePdf = new File(folder.getPath() + "\\" + Integer.toString(i) + ".pdf");
                File fileRtf = new File(folder.getPath() + "\\" + Integer.toString(i) + ".rtf");
                File fileDdd = new File(folder.getPath() + "\\" + Integer.toString(i) + ".ddd");
                try {
                    fileTxt.createNewFile();
                    filePdf.createNewFile();
                    fileRtf.createNewFile();
                    fileDdd.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Search searchFiles = new Search();
        List<File> files = searchFiles.files(parentDir.getPath(), Arrays.asList("txt", "ddd", "pdf", "rtf"));
        assertThat(files.size(), is(number * 4));
    }
}
