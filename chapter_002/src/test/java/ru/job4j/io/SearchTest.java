package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 29.01.2019
 */
public class SearchTest {

    private Search searchFiles = new Search();
    private File parentDir;
    private int number = 5;

    @Before
    public void setFileStructure() {
        String mainParent = System.getProperty("java.io.tmpdir");
        parentDir = new File(mainParent + File.separator + "java_search");
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }
        if (parentDir.exists()) {
            for (int i = 0; i < number; i++) {
                File folder = new File(parentDir.getPath()
                        + File.separator + Integer.toString(i));
                if (!folder.exists()) {
                    folder.mkdir();
                }
                File fileTxt = new File(folder.getPath()
                        + File.separator + Integer.toString(i) + ".txt");
                File filePdf = new File(folder.getPath()
                        + File.separator + Integer.toString(i) + ".pdf");
                File fileRtf = new File(folder.getPath()
                        + File.separator + Integer.toString(i) + ".rtf");
                File fileDdd = new File(folder.getPath()
                        + File.separator + Integer.toString(i) + ".ddd");
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
    }

    @Test
    public void whenGetFileListWithFourExtFromParent() {
        List<File> files = searchFiles.files(parentDir.getPath(), Arrays.asList("txt", "ddd", "pdf", "rtf"));
        assertThat(files.size(), is(number * 4));
    }

    @Test
    public void whenGetFileListWithTwoExtFromParent() {
        List<File> files = searchFiles.files(parentDir.getPath(), Arrays.asList("txt", "ddd"));
        assertThat(files.size(), is(number * 2));
    }

    @Test
    public void whenGetFileListWithNoExtFromParent() {
        List<File> files = searchFiles.files(parentDir.getPath(), new ArrayList<String>());
        assertThat(files.size(), is(0));
    }
}
