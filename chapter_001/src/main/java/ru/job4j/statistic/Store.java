package ru.job4j.statistic;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 21.11.2018
 */
public class Store {

    /**
     * Метод получает информацию об изменениях между начальным и текущим состояниями коллекций.
     *
     * @param previous Коллекция с начальными данными.
     * @param current  Коллекция с текущими данными.
     * @return Информация об изменениях.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        if (previous.size() != 0 || current.size() != 0) {
            info.fillParameters(previous, current);
        }
        return info;
    }

    /**
     * Класс, описывающий хранимые данные.
     */
    static class User {
        /**
         * Идентификатор.
         */
        private int id;
        /**
         * Наименование.
         */
        private String name;

        /**
         * Конструктор объекта.
         *
         * @param id   Идентификатор.
         * @param name Наименование.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Метод возвращает идентификатор.
         *
         * @return Идентификатор.
         */
        public int getId() {
            return id;
        }

        /**
         * Метод возвращает наименование.
         *
         * @return Наименование.
         */
        public String getName() {
            return name;
        }

        /**
         * Метод устанавливает наименование.
         *
         * @param name Новое наименование.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Метод выполняет сравнение объектов.
         *
         * @param object Сравниваемый объект.
         * @return Результат сравнения.
         */
        public boolean equals(Object object) {
            boolean isEqual = false;
            if (object instanceof User) {
                User otherUser = (User) object;
                isEqual = (this.getId() == otherUser.getId());
            }
            return isEqual;
        }

        /**
         * Метод возвращает хеш-код объекта.
         *
         * @return Хеш-код.
         */
        public int hashCode() {
            return this.getId() + this.getName().hashCode();
        }
    }
}
