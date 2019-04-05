package ru.job4j.jdbc.datamanipulate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author Valeriy Gyrievskikh
 * @since 04.04.2019
 */
public class ParserSAX extends DefaultHandler {

    /**
     * Результат расчета.
     */
    private int sum;

    /**
     * Метод выполняет парсинг файла.
     *
     * @param file Файл с данными.
     */
    public void parseFile(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            ParserSAX saxp = this;
            parser.parse(new File(file.getPath()), saxp);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выполняет чтение данных из нужного элемента.
     *
     * @param uri        Пространство имен.
     * @param localName  Локальное имя элемента.
     * @param qName      Комбинация локального имени и пространства имен.
     * @param attributes Атрибуты элемента.
     * @throws SAXException Ошибка чтения элемента.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("entry")) {
            String value = attributes.getValue("href");
            sum += Integer.parseInt(value);
        }
    }

    /**
     * Метод возвращает результат расчета.
     *
     * @return Результат расчета.
     */
    public int getSum() {
        return sum;
    }
}
