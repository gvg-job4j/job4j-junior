package ru.job4j.jdbc.datamanipulate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Valeriy Gyrievskikh
 * @since 02.04.2019
 */

@XmlRootElement
public class Entry {

    /**
     * Значение поля.
     */
    private int field;

    /**
     * Коструктор.
     */
    public Entry() {
    }

    /**
     * Конструктор, устанавливающий значение поля.
     *
     * @param field Значение поля.
     */
    public Entry(int field) {
        this.field = field;
    }

    /**
     * Метод устанавливает значение в поле.
     *
     * @param field Устанавливаемое значение.
     */
    public void setField(int field) {
        this.field = field;
    }

    /**
     * Метод возвращает значение поля.
     *
     * @return Значение поля.
     */
    public int getField() {
        return field;
    }
}
