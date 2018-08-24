package ru.job4j.generic;

import java.util.Iterator;

/**
 * @author Valeriy Gyrievskikh
 * Реализация общих методов для хранилищ.
 * @since 15.08.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Хранилище объектов.
     */
    private SimpleArray<T> store;

    /**
     * Конструктор, создает хранилище указанного размера.
     *
     * @param size Размер хранилища.
     */
    public AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    /**
     * Метод возвращает элемент хранилища.
     *
     * @param index Индекс ячейки хранилища.
     * @return Найденный элемент или null.
     */
    public T get(int index) {
        return store.get(index);
    }

    /**
     * Метод удаляет элемент по указанному индексу.
     *
     * @param index Индекс ячейки удаляемого элемента.
     */
    public void delete(int index) {
        this.store.delete(index);
    }

    /**
     * Метод очищает ячейку хранилища с указанным индексом.
     *
     * @param id Индекс ячейки.
     * @return Результат выполнения операции (true или false).
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int count = findIndexForId(id);
        if (count != -1) {
            delete(count);
            result = true;
        }
        return result;
    }

    /**
     * Метод добавляет объект в хранилище.
     *
     * @param model Добавляемый объект.
     */
    @Override
    public void add(T model) {
        store.add(model);
    }

    /**
     * Метод добавляет объект в хранилище в указанную ячейку.
     *
     * @param index Индекс ячейки.
     * @param model Добавляемый объект.
     */
    public void set(int index, T model) {
        store.set(index, model);
    }

    /**
     * Метод вставляет объект в ячейку с указанным индексом.
     *
     * @param id    Индекс ячейки.
     * @param model Вставляемый объект.
     * @return Результат выполнения операции (true или false).
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int count = findIndexForId(id);
        if (count != -1) {
            store.set(count, model);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает элемент с указанным идентификатором.
     *
     * @param id Идентификатор объекта.
     * @return Найденный объект, или null.
     */
    @Override
    public T findById(String id) {
        T element = null;
        int count = findIndexForId(id);
        if (count != -1) {
            element = get(count);
        }
        return element;
    }

    /**
     * Метод возвращает индекс объекта с указанным идентификатором в хранилище.
     *
     * @param id Идентификатор объекта.
     * @return Индекс ячейки, или -1.
     */
    private int findIndexForId(String id) {
        boolean find = false;
        Iterator<T> iterator = store.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element != null && element.getId().equals(id)) {
                find = true;
                break;
            }
            count++;
        }
        if (!find) {
            count = -1;
        }
        return count;
    }
}
