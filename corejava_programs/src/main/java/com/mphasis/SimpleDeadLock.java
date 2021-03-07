package org.example;

public class SimpleDeadLock {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();
    private int index;

    public static void main(String[] a) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {

        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}

            }
        }
    }

    private static class Thread2 extends Thread {

        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}


            }
        }
    }
}
