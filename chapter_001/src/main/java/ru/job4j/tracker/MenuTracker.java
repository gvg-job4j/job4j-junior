package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 24.06.2018.
 */
public class MenuTracker {

    /**
     * Запрашивает данные пользователя.
     */
    private Input input;

    /**
     * Система учета заявок.
     */
    private Tracker tracker;

    /**
     * Действия пользователя.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор, инициализирует поля класса.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Заполняет меню программы
     */
    public int[] fillActions() {
        this.actions.add(this.new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new EditItem());
        this.actions.add(this.new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new TrackerExit());
        this.actions.trimToSize();
        int[] keys = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) != null) {
                keys[i] = actions.get(i).key();
            }
        }
        return keys;
    }

    /**
     * Метод запускает выбранную процедуру.
     *
     * @param key Выбранный пункт меню.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Выводит меню программы
     */
    public void show() {
        for (UserAction action : this.actions
                ) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Внутренний класс, создает объект, выполняющий добавление заявки.
     */
    private class AddItem implements UserAction {

        /**
         * @return Пункт меню, соответсвующий методу данного класса.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Вызывает метод добавления новой заявки.
         *
         * @param input   Получение данных у пользователя.
         * @param tracker Система заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter the task's name:");
            String desc = input.ask("Enter the task's description :");
            Item item = tracker.add(new Item(name, desc));
            System.out.println("Создана новая заявка: " + item.toString());
        }

        /**
         * Метод выводит строку меню для данного объекта.
         *
         * @return Строка меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    /**
     * Внутренний статический класс, выполняет вывод всех заявок.
     */
    private static class ShowItems implements UserAction {

        /**
         * @return Пункт меню, соответсвующий методу данного класса.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Вызывает метод получения списка заявок.
         *
         * @param input   Получение данных у пользователя.
         * @param tracker Система заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> items = tracker.findAll();
            if (items != null) {
                for (Item item : tracker.findAll()
                        ) {
                    System.out.println("Заявка: " + item.toString());
                }
            } else {
                System.out.println("Заявок нет...");
            }
        }

        /**
         * Метод выводит строку меню для данного объекта.
         *
         * @return Строка меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    /**
     * Внутренний класс, создает объект, выполняющий удаление заявки по идентификатору.
     */
    private class DeleteItem implements UserAction {

        /**
         * @return Пункт меню, соответсвующий методу данного класса.
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Вызывает метод удаления заявки по идентификатору.
         *
         * @param input   Получение данных у пользователя.
         * @param tracker Система заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter the task's id:");
            Item item = tracker.findById(id);
            if (item != null) {
                tracker.delete(id);
                System.out.println("Удалена заявка: " + id);
            } else {
                System.out.println("Удаление не выполнено! Не найдена заявка с идентификатором: " + id);
            }
        }

        /**
         * Метод выводит строку меню для данного объекта.
         *
         * @return Строка меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete selected item.");
        }
    }

    /**
     * Внутренний статический класс, создает объект, выполняющий поиск заявки по идентификатору.
     */
    private static class FindItemById implements UserAction {

        /**
         * @return Пункт меню, соответсвующий методу данного класса.
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Вызывает метод поиска заявки по идентификатору.
         *
         * @param input   Получение данных у пользователя.
         * @param tracker Система заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter the task's id:");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Найдена заявка: " + item.toString());
            } else {
                System.out.println("Не найдена заявка с идентификатором: " + id);
            }
        }

        /**
         * Метод выводит строку меню для данного объекта.
         *
         * @return Строка меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id.");
        }
    }

    /**
     * Внутренний статический класс, вызов приводит к выходу из программы.
     */
    private static class TrackerExit implements UserAction {

        /**
         * @return Пункт меню, соответсвующий методу данного класса.
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         * Ничего не делает.
         *
         * @param input   Получение данных у пользователя.
         * @param tracker Система заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Выход из программы...");
        }

        /**
         * Метод выводит строку меню для данного объекта.
         *
         * @return Строка меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit.");
        }
    }
}

/**
 * Внешний класс, создает объект, выполняющий редактирование заявки.
 */
class EditItem implements UserAction {

    /**
     * @return Пункт меню, соответсвующий методу данного класса.
     */
    @Override
    public int key() {
        return 2;
    }

    /**
     * Вызывает метод редактирования выбранной заявки.
     *
     * @param input   Получение данных у пользователя.
     * @param tracker Система заявок.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter the task's id:");
        Item item = tracker.findById(id);
        if (item != null) {
            String name = input.ask("Enter the task's name:");
            String desc = input.ask("Enter the task's description :");
            item = new Item(name, desc);
            tracker.replace(id, item);
            System.out.println("Изменена заявка: " + item.toString());
        } else {
            System.out.println("Не найдена заявка с идентификатором: " + id);
        }
    }

    /**
     * Метод выводит строку меню для данного объекта.
     *
     * @return Строка меню.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit selected item.");
    }
}

/**
 * Внешний класс, создает объект, выполняющий поиск заявок по нименованию.
 */
class FindItemsByName implements UserAction {

    /**
     * @return Пункт меню, соответсвующий методу данного класса.
     */
    @Override
    public int key() {
        return 5;
    }

    /**
     * Вызывает метод поиска заявок по наименованию.
     *
     * @param input   Получение данных у пользователя.
     * @param tracker Система заявок.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Enter the task's name:");
        List<Item> items = tracker.findByName(name);
        if (items != null) {
            for (Item item : items
                    ) {
                System.out.println("Найдена заявка: " + item.toString());
            }
        } else {
            System.out.println("Не найдены заявки с наименованием: " + name);
        }
    }

    /**
     * Метод выводит строку меню для данного объекта.
     *
     * @return Строка меню.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Find items by name.");
    }
}
