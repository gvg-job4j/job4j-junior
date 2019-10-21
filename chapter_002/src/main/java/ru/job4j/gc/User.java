package ru.job4j.gc;

/**
 * @author Valeriy Gyrievskikh
 * @since 16.10.2019
 */
public class User {

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Конструктор, создает пользователя с указанным именем.
     *
     * @param name Имя пользователя.
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Метод показывает количество использованной памяти перед вызовом сборщика мусора.
     *
     * @throws Throwable Возможная ошибка.
     */
    @Override
    protected void finalize() throws Throwable {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()));
        System.out.println("GC running!");
        super.finalize();
    }
}
