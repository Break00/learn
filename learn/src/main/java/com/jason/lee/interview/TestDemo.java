package com.jason.lee.interview;

public class TestDemo {
    private int count;

    public static void main(String[] args) {
        TestDemo test = new TestDemo(88);
        System.out.println(test.count);
    }

    TestDemo(int a) {
        count = a;
    }
}
