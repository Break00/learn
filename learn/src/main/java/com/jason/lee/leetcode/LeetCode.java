package com.jason.lee.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class LeetCode {
    public static void main(String[] args) {
        ArrayList<String> list = bRGC(3);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 全排列
     *
     * @param num
     */
    private static void permutation(int[] num, int start) {
        if (start == num.length) {
            System.out.println(Arrays.toString(num));
        }
        for (int i = start; i < num.length; i++) {
            /**
             * 去重
             */
            if (i == start || num[i] != num[start]) {
                swap(num, start, i);
                permutation(num, start + 1);
                swap(num, start, i);
            }
        }
    }

    /**
     * 字典序全排列
     *
     * @param num
     */
    public static void sortedPermutation(int[] num) {
        Arrays.sort(num);
        while (true) {
            System.out.println(Arrays.toString(num));
            /**
             * 找出第一个比右边的数小的数 a(i)
             */
            int left = num.length - 1;
            for (int i = num.length - 1; i >= 1; i--) {
                if (num[i - 1] < num[i]) {
                    left = i - 1;
                    break;
                }
                // 全排列结束
                if (i == 1) {
                    return;
                }
            }
            /**
             * 在 i 右边找出大于a(i)的最小数
             */
            int right = num.length - 1;
            for (int i = num.length - 1; i > left; i--) {
                if (num[i] > num[left]) {
                    right = i;
                    break;
                }
            }

            // 交换
            swap(num, left, right);

            /**
             * 将 i 右侧的数反序
             */
            for (int i = left + 1, j = num.length - 1; i < j; i++, j--) {
                swap(num, i, j);
            }
        }

    }

    /**
     * 子集问题
     * A = {a(1),a(2)...a(n)}
     * 包含a(n)的子集 和 不包含a(n)的子集
     *
     * @param s
     * @return
     */
    public static ArrayList<String> combination(String s) {
        ArrayList<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            list.add(null);
            return list;
        }

        // 不包含a(n)的子集
        ArrayList<String> subList = combination(s.substring(1));

        // 包含a(n)的子集
        for (String s1 : subList) {
            if (s1 == null) {
                list.add(String.valueOf(s.charAt(0)));
            } else {
                list.add(String.valueOf(s.charAt(0)) + s1);
            }
        }
        // 合并
        list.addAll(subList);
        return list;
    }

    /**
     * 二进制反射格雷码
     * @param n
     * @return
     */
    public static ArrayList<String> bRGC(int n) {
        ArrayList<String> list = new ArrayList<>();
        if (n == 1) {
            list.add("0");
            list.add("1");
            return list;
        }

        ArrayList<String> subList = bRGC(n - 1);
        for (String s : subList) {
            list.add("0" + s);
        }
        for (String s : subList) {
            list.add("1" + s);
        }

        return list;
    }


    /**
     * 交换元素
     *
     * @param num
     * @param i
     * @param j
     */
    private static void swap(int num[], int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    /**
     * No.1 树的最小深度
     *
     * @param root
     * @return
     */
    public static int getMinDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = getMinDepth(root.left);
        int right = getMinDepth(root.right);
        if (left == 0 || right == 0) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * No.2 表达式求值
     * 栈
     *
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (tokens[i].equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (tokens[i].equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (tokens[i].equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.peek();
    }

    /**
     * No.3 直线上的点
     * 穷举
     *
     * @param points
     * @return
     */
    public static int maxPoints(Point[] points) {
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int cover = 0;
            int vertical = 0;
            int curMax = 1;
            HashMap<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                double x = points[j].x - points[i].x;
                double y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    cover++;
                } else if (x == 0) {
                    if (vertical == 0)
                        vertical = 2;
                    else
                        vertical++;
                    curMax = Math.max(curMax, vertical);
                } else {
                    double k = y / x;
                    if (!map.containsKey(k)) {
                        map.put(k, 2);
                    } else {
                        map.put(k, map.get(k) + 1);
                    }
                    curMax = Math.max(map.get(k), curMax);
                }
            }
            max = Math.max(max, curMax + cover);
        }
        return max;
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
