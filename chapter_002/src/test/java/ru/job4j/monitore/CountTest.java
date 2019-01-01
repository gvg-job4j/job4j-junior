package ru.job4j.monitore;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CountTest {
    /**
     * Класс описывает нить со счетчиком.
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем счетчик.
        final Count count = new Count();
        //Создаем нити.
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        Thread third = new ThreadCount(count);
        Thread fourth = new ThreadCount(count);
        Thread fifth = new ThreadCount(count);
        Thread sixth = new ThreadCount(count);
        //Запускаем нити.
        first.start();
        second.start();
        third.start();
        fourth.start();
        fifth.start();
        sixth.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();
        fourth.join();
        fifth.join();
        sixth.join();
        //Проверяем результат.
        assertThat(count.incremant(), is(6));
    }
}
