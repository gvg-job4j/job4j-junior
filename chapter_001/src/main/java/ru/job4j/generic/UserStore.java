package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Хранилище для объекта User.
 * @since 15.08.2018
 */
public class UserStore<User extends Base> extends AbstractStore<User> {

    /**
     * Конструктор, создает хранилище указанного размера.
     *
     * @param size Размер хранилища.
     */
    public UserStore(int size) {
        super(size);
    }
}
