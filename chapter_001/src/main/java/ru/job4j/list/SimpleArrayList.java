package ru.job4j.list;

/**
 * @author Valeriy Gyrievskikh
 * Реализация односвязного списка.
 * @since 23.08.2018
 */
public class SimpleArrayList<E> {
    /**
     * Размер списка.
     */
    private int size;
    /**
     * Первый элемент списка.
     */
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     *
     * @param date Вставляемые данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаляет первый элемент списка.
     *
     * @return null.
     */
    public E delete() {
        if (size > 0) {
            Node<E> element = this.first;
            if (element.next == null) {
                this.first = null;
            } else {
                while (element.next.next != null) {
                    element = element.next;
                }
                element.next = null;
            }
            this.size--;
        }
        return null;
    }

    /**
     * Метод получает данные по индексу.
     *
     * @param index Индекс данных.
     * @return Полученные данные.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     *
     * @return Размер коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        /**
         * Хранимые данные.
         */
        E date;
        /**
         * Следующий элемент коллекции.
         */
        Node<E> next;

        /**
         * Метод вставляет данные в элемент.
         *
         * @param date Вставляемые данные.
         */
        Node(E date) {
            this.date = date;
        }
    }
}
