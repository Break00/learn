package com.jason.lee.learn;

public class VolatileTest {
    public static void main(String[] args) {
        final Test test = new VolatileTest().new Test();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++)
                        test.inc();
                }
            }.start();
        }
        System.out.println(test.getNum());
    }

    class Test {
        private int num = 0;

        public void inc() {
            num++;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
