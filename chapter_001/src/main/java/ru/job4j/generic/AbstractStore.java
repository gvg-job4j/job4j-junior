package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Реализация общих методов для хранилищ.
 * @since 15.08.2018
 */
public abstract class AbstractStore<T> {
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
     * Метод возвращает хранилище объекта.
     *
     * @return
     */
    protected SimpleArray<T> getStore() {
        return store;
    }
}
