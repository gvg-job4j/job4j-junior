package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Хранилище для объекта User.
 * @since 15.08.2018
 */
public class UserStore<User> extends AbstractStore {
    /**
     * Хранилище объектов.
     */
    private SimpleArray<User> store;

    /**
     * Конструктор, создает хранилище указанного размера.
     *
     * @param size Размер хранилища.
     */
    public UserStore(int size) {
        super(size);
        this.store = super.getStore();
    }

    /**
     * Метод добавляет объект в хранилище.
     *
     * @param element Добавляемый элемент.
     */
    public void add(User element) {
        store.add(element);
    }

    /**
     * Метод добавляет объект в хранилище в указанную ячейку.
     *
     * @param index Индекс ячейки.
     * @param model Добавляемый объект.
     */
    public void set(int index, User model) {
        store.set(index, model);
    }
}
