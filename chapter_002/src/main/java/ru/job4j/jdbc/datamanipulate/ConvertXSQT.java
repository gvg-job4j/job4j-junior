package ru.job4j.jdbc.datamanipulate;

import javax.xml.transform.Source;
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
        Source xsl = new StreamSource(scheme);
        Source xml = new StreamSource(source);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsl);
        transformer.transform(xml, new StreamResult(new FileOutputStream(dest)));
    }
}
