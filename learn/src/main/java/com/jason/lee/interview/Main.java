package com.jason.lee.interview;


public class Main {

    public static void main(String[] args) {
//        System.out.println(recSubSet(new int[]{3, 34, 4, 12, 5, 2}, 5, 13));
//        System.out.println(dpSubSet(new int[]{3, 34, 4, 12, 5, 2}, 9));
//        String s = "123456-9527";
//        System.out.println(s.substring(0, s.length() - 5));
    }

    /**
     * 最大公约数
     * 辗转相除法
     *
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisor(int a, int b) {
        return b == 0 ? a : getGreatestCommonDivisor(b, a % b);
    }

    /**
     * 最小公倍数
     * a*b/gcd
     *
     * @param a
     * @param b
     * @return
     */
    public static int getLeastCommonMultiplier(int a, int b) {
        return a * b / getGreatestCommonDivisor(a, b);
    }

    /**
     * 判断是否为素数
     *
     * @param num
     * @return
     */
    public static boolean isPrime1(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    /**
     * 判断是否为素数
     * 埃拉托斯特尼筛法
     *
     * @param n
     * @return
     */
    public static boolean isPrime2(int n) {

        boolean[] flag = new boolean[n + 1];

        //初始化为素数
        for (int i = 2; i < n + 1; i++) {
            flag[i] = true;
        }

        //从2开始，将素数的倍数标记为非素数
        for (int i = 2; i <= Math.sqrt(n); i++) {

            //不是素数，说明i可以分解为两个因子相乘，那么在遇到这两个因子的较小者时，i的倍数已经被标记过
            if (flag[i] == false)
                continue;

            //从i的平方开始标记即可，不需要从i*j(且j<i)开始，因为i*j至少在遇到j时已经被标记过了
            //j是i的倍数
            for (int j = i * i; j <= Math.sqrt(n); j += i) {
                flag[j] = false;
            }
        }
        return flag[n];
    }

    /**
     * 多任务最大收益
     *
     * @param task
     * @return
     */
    public static int getMaxValue(Task[] task) {
        int[] preTask = new int[task.length];
        int[] optimize = new int[task.length];
        //当前任务前无符合要求的任务
        preTask[0] = -1;
        optimize[0] = task[0].value;
        //初始化
        for (int i = 1; i < task.length; i++) {
            preTask[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (task[j].end <= task[i].start) {
                    preTask[i] = j;
                    break;
                }
            }
        }
        //当前任务选不选的问题
        for (int i = 1; i < task.length; i++) {
            if (preTask[i] == -1) {
                optimize[i] = Math.max(optimize[i - 1], task[i].value);
            } else {
                optimize[i] = Math.max(optimize[i - 1], (task[i].value + optimize[preTask[i]]));
            }
        }
        return optimize[task.length - 1];
    }

    /**
     * 任务
     */
    private static class Task {
        int start;
        int end;
        int value;
    }

    /**
     * 数组不相邻的数的最大和
     * 解法一 递归
     *
     * @param num
     * @param i
     * @return
     */
    public static int recGetNextSum(int[] num, int i) {
        if (i == 0)
            return num[0];
        else if (i == 1) {
            return Math.max(num[0], num[1]);
        } else {
            return Math.max(recGetNextSum(num, i - 2) + num[i], recGetNextSum(num, i - 1));
        }
    }

    /**
     * 解法二 动态规划
     *
     * @param num
     * @return
     */
    public static int dpGetNextSum(int[] num) {
        int[] dp = new int[num.length];
        dp[0] = num[0];
        dp[1] = Math.max(num[0], num[1]);
        for (int i = 2; i < num.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + num[i]);
        }
        return dp[num.length - 1];
    }

    /**
     * 数组是否存在元素的和为给定值
     * 解法一 递归
     *
     * @param num
     * @param i
     * @param s
     * @return
     */
    public static boolean recSubSet(int[] num, int i, int s) {
        if (s == 0)
            return true;
        else if (i == 0)
            return num[0] == s;
        else if (num[i] > s)
            return recSubSet(num, i - 1, s);
        else
            return recSubSet(num, i - 1, s - num[i]) || recSubSet(num, i - 1, s);
    }

    /**
     * 解法二 动态规划
     *
     * @param num
     * @param s
     * @return
     */
    public static boolean dpSubSet(int[] num, int s) {
        boolean[][] dp = new boolean[num.length][s + 1];
        if (num[0] <= s)
            dp[0][num[0]] = true;
        for (int i = 1; i < num.length; i++) {
            for (int j = 0; j <= s; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                }
                if (num[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num[i]];
                }
            }
        }
        return dp[num.length - 1][s];
    }
}