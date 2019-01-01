package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Valeriy Gyrievskikh
 * Реализация многопоточного счетчика.
 * @since 18.12.2018
 */
@ThreadSafe
public class Count {
    /**
     * Значение счетчика.
     */
    @GuardedBy("this")
    private int value;

    /**
     * Метод возвращает текущее значение счетчика.
     *
     * @return Значение счетчика.
     */
    public synchronized int incremant() {
        return this.value;
    }

    /**
     * Метод выполняет увеличение текущего значения счетчика на 1.
     */
    public synchronized void increment() {
        this.value++;
    }
}
