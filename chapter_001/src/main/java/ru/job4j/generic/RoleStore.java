package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Хранилище для объекта Role.
 * @since 15.08.2018
 */
public class RoleStore<Role> extends AbstractStore {
    /**
     * Хранилище объектов.
     */
    private SimpleArray<Role> store;

    /**
     * Конструктор, создает хранилище указанного размера.
     *
     * @param size Размер хранилища.
     */
    public RoleStore(int size) {
        super(size);
        this.store = super.getStore();
    }

    /**
     * Метод добавляет объект в хранилище.
     *
     * @param element Добавляемый элемент.
     */
    public void add(Role element) {
        store.add(element);
    }

    /**
     * Метод добавляет объект в хранилище в указанную ячейку.
     *
     * @param index Индекс ячейки.
     * @param model Добавляемый объект.
     */
    public void set(int index, Role model) {
        store.set(index, model);
    }
}
