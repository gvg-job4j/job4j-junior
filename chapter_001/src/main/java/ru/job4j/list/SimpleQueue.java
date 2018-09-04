package ru.job4j.list;

/**
 * Очередь на базе связного списка.
 *
 * @author Valeriy Gyrievskikh
 * @since 03.09.2018
 */
public class SimpleQueue<E> {
    /**
     * Хранилище данных.
     */
    private SimpleArrayList<E> container;

    /**
     * Конструктор, слздает новое хранилище.
     */
    public SimpleQueue() {
        this.container = new SimpleArrayList<>();
    }

    /**
     * Получает элемент и удалаяет его из хранилища.
     *
     * @return Полученный элемент или исключение.
     */
    public E poll() {
        E element = this.container.get(this.container.getSize() - 1);
        this.container.delete();
        return element;
    }

    /**
     * Помещает элемент в хранилище.
     *
     * @param value Элемент.
     */
    public void push(E value) {
        this.container.add(value);
    }
}
