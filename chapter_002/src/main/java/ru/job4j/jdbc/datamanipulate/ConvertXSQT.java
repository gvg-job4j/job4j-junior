package ru.job4j.jdbc.datamanipulate;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.04.2019
 */
public class ConvertXSQT {

    /**
     * Конструктор.
     */
    public ConvertXSQT() {
    }

    /**
     * Метод конвертирует вхоядщий файл в соответствии с заданной схемой.
     *
     * @param source Входящий файл.
     * @param dest   Файл с преобразованными данными.
     * @param scheme Схема конвертации.
     * @throws TransformerException  Ошибка трансформации.
     * @throws FileNotFoundException Ошибка чтения файла.
     */
    public void convert(File source, File dest, File scheme) throws TransformerException, FileNotFoundException {
        String xsl = getStringFromFile(scheme);
        String xml = getStringFromFile(source);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(new ByteArrayInputStream(xsl.getBytes())));
        transformer.transform(new StreamSource(new ByteArrayInputStream(xml.getBytes())),
                new StreamResult(new FileOutputStream(dest)));
    }

    /**
     * Метод формирует строку из файла.
     *
     * @param file Файл с данными.
     * @return Строка.
     */
    private String getStringFromFile(File file) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int c;
            while ((c = br.read()) != -1) {
                text.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
