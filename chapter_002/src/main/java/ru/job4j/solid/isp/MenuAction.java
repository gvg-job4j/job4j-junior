package ru.job4j.solid.isp;

/**
 * @author Valeriy Gyrievskikh
 * @since 07.08.2019
 */
public interface MenuAction {
    /**
     * Метод запускает действие.
     *
     * @return Результат выподнения.
     */
    public boolean execute();
}
