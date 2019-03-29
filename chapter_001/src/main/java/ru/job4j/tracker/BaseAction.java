package ru.job4j.tracker;

/**
 * @author Valeriy Gyrievskikh.
 * @since 30.06.2018.
 */
public abstract class BaseAction implements UserAction {

    /**
     * Пункт меню.
     */
    private final int key;

    /**
     * Описание пункта меню.
     */
    private final String name;

    /**
     * Конструктор.
     *
     * @param key  Пункт меню.
     * @param name Описание пункта меню.
     */
    public BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Метод возвращает значение выбранного пункта меню.
     *
     * @return Выбранный пункт меню.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Метод возвращает описание выбранного пункта меню.
     *
     * @return Описание выбранного пункта меню.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
