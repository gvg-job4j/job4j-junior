package ru.job4j.tracker;

/**
 * @author Valeriy Gyrievskikh
 * @since 19.06.2018.
 */
public class Item {
    /**
     * Уникальный идентификатор заявки.
     */
    private String id;

    /**
     * Наименование заявки.
     */
    private String name;

    /**
     * Описание заявки.
     */
    private String description;

    /**
     * Номер заявки.
     */
    private long number;

    /**
     * Конструктор заявки по наименованию и описанию.
     *
     * @param name        Наименование.
     * @param description Описание.
     */
    public Item(String name, String description) {
        this(name, description, 1);
    }

    /**
     * Конструктор заявки по наименованию, описанию и номеру.
     *
     * @param name        Наименование.
     * @param description Описание.
     * @param number      Номер.
     */
    public Item(String name, String description, long number) {
        this.name = name;
        this.description = description;
        this.number = number;
    }

    /**
     * Метод возвращает идентификатор заявки.
     *
     * @return Идентификатор
     */
    public String getId() {
        return id;
    }

    /**
     * Метод устанавливает идентификатор заявки.
     *
     * @param id Идентификатор.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Метод возвращает наименование заявки.
     *
     * @return Наименование.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод устанавливает наименование заявки.
     *
     * @param name Наименование.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает описание заявки.
     *
     * @return Описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод устанавливает описание заявки.
     *
     * @param description Описание.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод возвращает номер заявки.
     *
     * @return Номер.
     */
    public long getNumber() {
        return number;
    }

    /**
     * Метод устанавливает номер заявки.
     *
     * @param number Номер.
     */
    public void setNumber(long number) {
        this.number = number;
    }

    public String toString() {
        return String.format("%s. %s", this.getId(), this.getName());
    }
}
