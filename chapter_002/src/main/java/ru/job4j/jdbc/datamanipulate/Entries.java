package ru.job4j.jdbc.datamanipulate;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 03.04.2019
 */

@XmlRootElement(name = "Entries")
public class Entries {
    /**
     * Список записей.
     */
    private List<Entry> entry;

    /**
     * Конструктор, устанавливает список записей.
     *
     * @param entry Входящий список.
     */
    public Entries(List<Entry> entry) {
        this.entry = entry;
    }

    /**
     * Метод возвращает список значений.
     *
     * @return Хранящийся список значений.
     */
    public List<Entry> getEntry() {
        return entry;
    }

    /**
     * Метод устанавливает список значений.
     *
     * @param entry Входящий список.
     */
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    /**
     * Конструктор.
     */
    public Entries() {

    }
}
