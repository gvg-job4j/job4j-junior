package ru.job4j.thread_problem;

import org.junit.Test;

public class SimplePingPongTest {

    @Test
    public void startPingPong() {

        PingPong printer = new PingPong();
        Thread threadPing = new Thread(() -> {
            int count = 0;
            while (count < 20) {
                printer.printString("Ping");
                count++;
            }
            printer.closeThreads();
        });
        Thread threadPong = new Thread(() -> {
            int count = 0;
            while (count < 20) {
                printer.printString("Pong");
                count++;
            }
            printer.closeThreads();
        });
        threadPing.start();
        threadPong.start();
    }
}
