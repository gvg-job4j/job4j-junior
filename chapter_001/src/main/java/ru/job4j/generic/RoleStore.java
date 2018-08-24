package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Хранилище для объекта Role.
 * @since 15.08.2018
 */
public class RoleStore<Role extends Base> extends AbstractStore<Role> {
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
        this.store = new SimpleArray<>(size);
    }
}
