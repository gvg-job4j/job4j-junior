package ru.job4j.list;

/**
 * Стек на базе связного списка.
 *
 * @author Valeriy Gyrievskikh
 * @since 03.09.2018
 */
public class SimpleStack<E> {
    /**
     * Хранилище данных.
     */
    private SimpleArrayList<E> container;

    /**
     * Конструктор, слздает новое хранилище.
     */
    public SimpleStack() {
        this.container = new SimpleArrayList<>();
    }

    /**
     * Получает элемент и удалаяет его из хранилища.
     *
     * @return Полученный элемент или исключение.
     */
    public E poll() {
        E element = this.container.get(0);
        int size = this.container.getSize();
        SimpleArrayList<E> newContainer = new SimpleArrayList<>();
        for (int i = size - 1; i > 0; i--) {
            newContainer.add(this.container.get(i));
        }
        this.container = newContainer;
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
