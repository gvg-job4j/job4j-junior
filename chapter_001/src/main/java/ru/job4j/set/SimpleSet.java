package ru.job4j.set;

import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * @author Valeriy Gyrievskikh
 * Реализация коллекции Set на массиве, с использованием ранее созданного класса.
 * @since 18.09.2018
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * Хранилище данных.
     */
    private DynamicList<E> list;
    /**
     * Текущая позиция.
     */
    private int count;

    /**
     * Конструктор, создает пустое хранилище.
     */
    public SimpleSet() {
        list = new DynamicList<>();
        count = 0;
    }

    /**
     * Метод добавляет новый элемент в список. Добавляются только элементы,
     * которых нет в списке.
     *
     * @param newElement Добавляемый элемент.
     */
    public void add(E newElement) {
        boolean alreadyHas = false;
        for (int i = 0; i < count; i++) {
            E element = list.get(i);
            if (newElement.equals(element)) {
                alreadyHas = true;
                break;
            }
        }
        if (!alreadyHas) {
            list.add(newElement);
            count++;
        }
    }

    /**
     * Метод возвращает итератор.
     *
     * @return Итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
