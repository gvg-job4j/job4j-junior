package ru.job4j.jdbc.datamanipulate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 31.03.2019
 */

public class StoreXML {

    /**
     * Файл, в который будут сохранены данные.
     */
    private File file;

    /**
     * Конструктор, устанавливает файл для сохранения данных.
     *
     * @param file Файл для сохранения данных.
     */
    public StoreXML(File file) {
        this.file = file;
    }

    /**
     * Метод выполняет сохранение данных, полученных из БД, в файл в формате XML.
     *
     * @param list Список данных из БД.
     */
    public void save(List<Entry> list) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), this.file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
