package ru.job4j.threadproblem;

public class PingPong {

    public synchronized void closeThreads() {
        notifyAll();
    }

    //        public synchronized void printString(String string) {
    public void printString(String string) {
        System.out.println("Thread " + Thread.currentThread().getName() + " print: " + string);
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
