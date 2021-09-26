package com.jason.lee.learn;

public class ThreadLocalTest {

    private static ThreadLocal<String> local1 = new ThreadLocal<String>();
    private static ThreadLocal<Double> local2 = new ThreadLocal<Double>();

    public static void main(String[] args) {
        Task task = new Task();
        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }

    static class Task implements Runnable {
        public void run() {
            local1.set(Thread.currentThread().getName());
            local2.set(Math.random());
            System.out.println("当前线程" + local1.get() + ": " + local2.get());
        }
    }
}

