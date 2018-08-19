package ru.job4j.generic;

/**
 * @author Valeriy Gyrievskikh
 * Интерфейс хранилища.
 * @since 15.08.2018
 */
public interface Store<T extends Base> {
    /**
     * Метод добавляет объект в хранилище.
     *
     * @param model Добавляемый элемент.
     */
    void add(T model);

    /**
     * Метод заменяет объект в указанной ячейке.
     *
     * @param id    Индекс ячейки.
     * @param model Вставляемый объект.
     * @return Результат выполнения (true или false).
     */
    boolean replace(String id, T model);

    /**
     * Метод удаляет элемент в указанной ячейке.
     *
     * @param id Индекс ячейки.
     * @return Результат выполнения (true или false).
     */
    boolean delete(String id);

    /**
     * Метод ищет объект с указанным идентификатором.
     *
     * @param id Идентификатор объекта.
     * @return Найденный объект или null.
     */
    T findById(String id);
}
