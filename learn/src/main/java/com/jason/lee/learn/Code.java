package com.jason.lee.learn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code {
    private static TreeNode root;
    private static TreeNode head;
    private static TreeNode last;

    public static void main(String[] args) {
        int[] num = {2, 4, 1, 3, 7, 6, 9, 1};
        String[] str = {"aesd", "bcnd", "axkf"};
        Sort sort = new Sort();
//        sort.bubbleSort(num);
//        sort.insertSort(num);
//        sort.shellSort(num);
//        sort.selectSort(num);
//        sort.heapSort(num);
//        sort.mergeSort(num);
//        sort.quickSort(0,num.length-1,num);
//        sort.bubbleSort(num);
//        sort.radixSort(str,4);
//        for(int n:num)
//            System.out.print(n);
        for (String s : str)
            System.out.println(s);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(1);
        queue.poll();
    }

    /**
     * 逆序栈
     *
     * @param stack
     * @param top
     * @return
     */
    public static int[] reverseStack(int[] stack, int top) {
        if (top == 0)
            return stack;
        int num = getAndRemoveLast(stack, top);
        top--;
        stack = reverseStack(stack, top);
        stack[top] = num;
        return stack;
    }

    public static int getAndRemoveLast(int[] stack, int top) {
        int res = stack[--top];
        if (top == 0)
            return res;
        int last = getAndRemoveLast(stack, top);
        stack[--top] = res;
        return last;
    }

    /**
     * 树的高度     从下至上
     * 后序遍历
     *
     * @param root
     * @return
     */
    public static int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        int rootHeight = Math.max(leftHeight, rightHeight) + 1;
        return rootHeight;
    }

    /**
     * 树的深度     从上至下
     * 前序遍历
     *
     * @param root
     * @return
     */
    public static int getDepth(TreeNode root, int depth) {
        return depth;

    }

    /**
     * 节点总数
     *
     * @param root
     * @return
     */
    public static int getNodes(TreeNode root) {
        if (root == null)
            return 0;
        int leftNodes = getNodes(root.left);
        int rightNodes = getNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    /**
     * get某个节点的父节点
     *
     * @param node
     * @return
     */
    public static TreeNode getParent(TreeNode node) {
        if (node == null || node == root)
            return null;
        else
            return getParent(root, node);
    }

    public static TreeNode getParent(TreeNode root, TreeNode node) {
        if (root == null)
            return null;
        if (root.left == node || root.right == node)
            return root;
        TreeNode parent = getParent(root.left, node);
        return parent != null ? parent : getParent(root.right, node);
    }

    /**
     * 二叉树非递归前序遍历
     *
     * @param root
     */
    public static ArrayList preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<TreeNode> lt = new ArrayList<TreeNode>();
        if (root == null)
            return lt;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            lt.add(node);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return lt;
    }

    /**
     * 二叉树非递归中序遍历
     *
     * @param root
     */
    public static void inOrder(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    /**
     * 二叉树非递归后序遍历
     * 两个栈辅助
     *
     * @param root
     */
    public static void postOrder(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);
            if (node.left != null)
                s1.push(node.left);
            if (node.right != null)
                s1.push(node.right);
        }
        while (!s2.isEmpty()) {
            System.out.println(s2.pop());
        }
    }

    /**
     * 1....n的数字组成搜索二叉树的种数
     *
     * @param n
     * @return
     */
    public static int getNumOfBST(int n) {
        if (n == 0)
            return 1;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += getNumOfBST(i - 1) * getNumOfBST(n - i);
        }
        return res;
    }

    /**
     * 二叉搜索树删除
     *
     * @param root
     * @param node
     */
    public static void delNodeOfTree(TreeNode root, TreeNode node) {

    }

    /**
     * 将二叉搜索树转换为双向链表
     *
     * @param root
     */
    public static TreeNode convertBSTToLinkedList(TreeNode root) {
        InOrder(root);
        return head;
    }

    public static void InOrder(TreeNode node) {
        if (node == null)
            return;
        InOrder(root.left);
        if (last == null) {
            head = node;
            last = node;
        } else {
            node.left = last;
            last.right = node;
            last = node;
        }
        InOrder(root.right);
    }
}

/**
 * 排序
 */
class Sort {
    /**
     * 冒泡排序
     *
     * @param num
     */
    public void bubbleSort(int[] num) {
        boolean flag = true;
        for (int i = 0; i < num.length && flag; i++) {
            flag = false;
            for (int j = 0; j < num.length - i - 1; j++) {
                if (num[j] > num[j + 1]) {
                    int temp = num[j + 1];
                    num[j + 1] = num[j];
                    num[j] = temp;
                    flag = true;
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param num
     */
    public void insertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int temp = num[i];
            int j = i;
            for (; j > 0 && temp < num[j - 1]; j--) {
                num[j] = num[j - 1];
            }
            num[j] = temp;
        }
    }

    /**
     * 希尔排序
     *
     * @param num
     */
    public void shellSort(int[] num) {
        for (int gap = num.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < num.length; i++) {
                int temp = num[i];
                int j = i;
                for (; j - gap >= 0 && temp < num[j - gap]; j -= gap) {
                    num[j] = num[j - gap];
                }
                num[j] = temp;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param num
     */
    public void selectSort(int[] num) {
        for (int i = 0; i < num.length - 1; i++) {
            int min = num[i];
            int index = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] < min) {
                    min = num[j];
                    index = j;
                }
            }
            num[index] = num[i];
            num[i] = min;
        }
    }

    /**
     * 堆排序
     *
     * @param num
     */
    public void heapSort(int[] num) {
        for (int i = num.length / 2 - 1; i >= 0; i--)
            percolateDown(i, num.length, num);

        for (int j = num.length - 1; j > 0; j--) {
            int temp = num[j];
            num[j] = num[0];
            num[0] = temp;
            percolateDown(0, j, num);
        }
    }

    private void buildMaxHeap(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int temp = num[i];
            int hole = i;
            for (; temp > num[(hole - 1) / 2] && hole > 0; hole = (hole - 1) / 2) {
                num[hole] = num[(hole - 1) / 2];
            }
            num[hole] = temp;
        }
    }

    private void percolateDown(int hole, int size, int[] num) {
        int temp = num[hole];
        int child;
        for (; 2 * hole + 1 < size; hole = child) {
            child = 2 * hole + 1;
            if (child + 1 < size && num[child] < num[child + 1])
                child++;
            if (num[child] > temp)
                num[hole] = num[child];
            else
                break;
        }
        num[hole] = temp;
    }

    /**
     * 归并排序
     *
     * @param num
     */
    public void mergeSort(int[] num) {
        int[] temp = new int[num.length];
        mergerSort(num, temp, 0, num.length - 1);
    }

    private void mergerSort(int[] num, int[] temp, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            mergerSort(num, temp, begin, mid);
            mergerSort(num, temp, mid + 1, end);
            merge(num, temp, begin, mid, end);
        }
    }

    private void merge(int[] num, int[] temp, int begin, int mid, int end) {
        int index = begin;
        int right = mid + 1;
        int number = end - begin + 1;
        while (begin <= mid && right <= end) {
            if (num[begin] < num[right])
                temp[index++] = num[begin++];
            else
                temp[index++] = num[right++];
        }
        while (begin <= mid)
            temp[index++] = num[begin++];
        while (right <= end)
            temp[index++] = num[right++];
        for (int i = 0; i < number; i++, end--) {
            num[end] = temp[end];
        }
    }

    /**
     * 快速排序
     *
     * @param begin
     * @param end
     * @param num
     */
    public void quickSort(int begin, int end, int[] num) {
        if (begin < end) {
            int p = partion(begin, end, num);
            quickSort(begin, p - 1, num);
            quickSort(p + 1, end, num);
        }
    }

    private int partion(int begin, int end, int[] num) {
        int point = num[begin];
        while (begin < end) {
            while (begin < end && num[end] >= point)
                end--;
            num[begin] = num[end];
            while (begin < end && num[begin] <= point)
                begin++;
            num[end] = num[begin];
        }
        num[begin] = point;
        return begin;
    }

    /**
     * 桶排序
     *
     * @param num
     */
    public void bucketSort(int[] num) {
        int max = num[0];
        int index = 0;
        for (int n : num)
            if (n > max)
                max = n;
        int[] bucket = new int[max + 1];
        for (int n : num)
            bucket[n]++;
        for (int i = 0; i <= max; i++)
            while (bucket[i]-- != 0)
                num[index++] = i;
    }
    /**
     * 基数排序 &&& 计数排序
     */

}

class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int val) {
        this.left = null;
        this.right = null;
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "left=" + left +
                ", right=" + right +
                ", val=" + val +
                '}';
    }
}

class Node {
    public Object data;
    public int index;

    public Node(Object data, int index) {
        this.data = data;
        this.index = index;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}