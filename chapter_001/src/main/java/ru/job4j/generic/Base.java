package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Описание модели.
 * @since 15.08.2018
 */
public abstract class Base {
    /**
     * Идентификатор объекта.
     */
    private final String id;

    /**
     * Конструктор, создает объект с указанным идентификатором.
     *
     * @param id Идентификатор.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Метод возвращает идентификатор объекта.
     *
     * @return Идентификатор.
     */
    public String getId() {
        return id;
    }
}
