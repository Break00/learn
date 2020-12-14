package com.jason.lee.offer;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Offer {

    private static boolean isBalanced = true;
    private static ArrayList<TreeNode> nodes = new ArrayList<>();
    private static int countOfPairs = 0;
    private static HashSet<String> set = new HashSet<>();
    private static char[] tab = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws Exception {

//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//
//        printLinkedListI(head);
//        printLinkedListII(head);

//        buildTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});

        LocalTime time = LocalTime.now();
        LocalTime endTime = LocalTime.of(23, 59, 59);

        long second = endTime.toSecondOfDay() - time.toSecondOfDay();

        System.out.println(time);
        System.out.println(endTime);
        System.out.println(second);


    }


    /**
     * No.2 单例模式
     */
    private static volatile Offer singleton;

    /**
     * 构造函数私有化
     */
    private Offer() {
    }

    /**
     * 双重检查
     *
     * @return
     */
    public static Offer getSingleton() {
        if (singleton == null) {
            synchronized (Offer.class) {
                if (singleton == null) {
                    singleton = new Offer();
                }
            }
        }
        return singleton;
    }

    /**
     * No.3 任意重复的数
     *
     * @param nums
     * @return
     */
    public static int getRepeatedNum(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                int temp = nums[i];
                if (nums[temp] == temp) {
                    return temp;
                } else {
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }

        }
        return -1;
    }

    /**
     * 不改变数组找出重复的数
     * n+1 的数组  1～n 的数
     * 空间 o(N)
     *
     * @param nums
     * @return
     */
    public static int getRepeatedN(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
            if (arr[nums[i]] > 1) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 解法二
     * 空间 o(1)
     *
     * @param nums
     * @return
     */
    public static int getRepeatedM(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (end >= start) {
            int mid = (end - start) >> 1 + start;

            int count = countRange(nums, len, start, mid);

            if (start == end) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }

            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] nums, int len, int start, int end) {
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }

    /**
     * No.4 二维数组中的查找
     * 行/列 有序
     * 矩阵问题考虑四个顶点
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean isExist(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[row][col])
            return false;
        int i = 0, j = col;
        while (j >= 0 && i <= row) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] < target)
                i++;
            else
                j--;
        }
        return false;
    }

    /**
     * No.5 替换空格
     * API
     *
     * @param s
     * @return
     */
    public static String replaceSpaceI(String s) {
        return s.replace(" ", "%20");
    }

    /**
     * 解法二
     *
     * @param s
     * @return
     */
    public static String replaceSpaceII(String s) {
        char[] origin = s.toCharArray();
        int lenO = s.length();
        int countSpace = 0;
        // 统计空格数
        for (int i = 0; i < lenO; i++) {
            if (origin[i] == ' ') {
                countSpace++;
            }
        }
        char[] current = new char[lenO + 2 * countSpace];
        System.arraycopy(origin, 0, current, 0, lenO);
        int lenC = current.length;
        lenC = lenC - 1;
        lenO = lenO - 1;
        while (lenC > lenO) {
            if (current[lenO] != ' ') {
                current[lenC--] = current[lenO--];
            } else {
                current[lenC--] = '0';
                current[lenC--] = '2';
                current[lenC--] = '%';
                lenO--;
            }
        }
        return String.valueOf(current);
    }

    /**
     * 补充：两排序数组合并
     * 从后往前！！！
     *
     * @param A1
     * @param A2
     * @return
     */
    public static int[] concatOrderArray(int[] A1, int[] A2) {
        int index = A1.length - 1;
        int j = A2.length - 1;
        int i = index - j - 1;
        while (j >= 0) {
            if (A2[j] >= A1[i]) {
                A1[index--] = A2[j--];
            } else {
                A1[index--] = A1[i--];
            }
        }
        return A1;
    }

    /**
     * N0.6 从尾到头打印链表
     *
     * @param head
     */
    public static void printLinkedListI(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        ListNode node = head;
        while (node != null) {
            s.push(node);
            node = node.next;
        }

        while (!s.isEmpty()) {
            System.out.println(s.pop().val);
        }
    }

    /**
     * 解法二 递归
     *
     * @param head
     */
    public static void printLinkedListII(ListNode head) {
        if (head == null)
            return;
        printLinkedListII(head.next);
        System.out.println(head.val);
    }

    /**
     * No.7 重建二叉树
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return buildTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private static TreeNode buildTree(int[] preOrder, int startP, int endP, int[] inOrder, int startI, int endI) {

        if (startP > endP) {
            return null;
        }

        int rootVal = preOrder[startP];
        TreeNode root = new TreeNode(rootVal);

        int index = 0;
        while (inOrder[index] != rootVal) {
            index++;
        }

        root.left = buildTree(preOrder, startP + 1, startP + index - startI, inOrder, startI, index - 1);
        root.right = buildTree(preOrder, startP + index - startI + 1, endP, inOrder, index + 1, endI);

        return root;


    }

    /**
     * No.8 二叉树的下一个节点
     * 中序遍历
     *
     * @param root
     * @param target
     * @return
     */
    public static TreeNode nextNodeI(TreeNode root, TreeNode target) {
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 0; i < list.size(); i++) {
            TreeNode current = list.get(i);
            if (current.val == target.val && i + 1 < list.size()) {
                return list.get(i + 1);
            }
        }
        return null;
    }

    private static void inOrder(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null)
            return;
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }

    /**
     * No.8 二叉树的下一个节点
     * 中序遍历
     *
     * @param target
     * @return
     */
    public static TreeNode nextNodeII(TreeNode target) {
        if (target == null)
            return null;

        TreeNode next = null;

        // 右子树存在，取右子树的最左节点
        if (target.right != null) {
            TreeNode right = target.right;
            while (right.left != null) {
                right = right.left;
            }
            next = right;
        }
        // 右子树不存在，取其父节点路径中拥有左子节点的节点
        else {
            if (target.parent != null) {
                TreeNode current = target;
                TreeNode parent = target.parent;
                while (parent != null && current == parent.right) {
                    current = parent;
                    parent = parent.parent;
                }
                next = parent;
            }
        }
        return next;
    }

    /**
     * No.10 斐波那契数列
     * 递归
     *
     * @param n
     * @return
     */
    public static int fibonacciI(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacciI(n - 1) + fibonacciI(n - 2);
    }

    /**
     * 解法二 动态规划
     *
     * @param n
     * @return
     */
    public static int fibonacciII(int n) {
        int[] fibonacci = new int[n + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci[n];

    }

    /**
     * 跳台阶
     * 1、2、3、4...n
     * f(n) = f(n-1) + f(n-2) + ... + f(2) + f(1)
     * f(n) = 2^(n-1)  数学归纳法
     *
     * @param n
     * @return
     */
    public static int jumpSteps(int n) {
        return 1 << (n - 1);
    }

    /**
     * No.11 旋转(有序)数组中最小的数字
     * 二分法 + 特例！！！
     * @param num
     * @return
     */
    public static int minOfRotateArray(int[] num) {
        int p1 = 0;
        int p2 = num.length - 1;
        int mid = p1;
        while (num[p1] >= num[p2]) {
            if (p2 - p1 == 1) {
                mid = p2;
                break;
            }

            mid = (p2 - p1) >> 1 + p1;

            // 三个未知的数均相等
            if (num[p1] == num[mid] && num[mid] == num[p2]) {
                return getMinOfArray(num);
            }
            else {
                if (num[mid] > num[p1]) {
                    p1 = mid;
                }
                else {
                    p2 = mid;
                }
            }
        }
        return num[mid];
    }

    private static int getMinOfArray(int[] num) {
        int min = Integer.MAX_VALUE;
        for (int n : num) {
            min = n < min ? n : min;
        }
        return min;
    }

    /**
     * No.14 剪绳子
     * f(n)=max(f(i)*f(n-i))  0<i<n
     *
     * @param n
     * @return
     */
    public static int cutRope1(int n) {
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        int max = 0;
        int[] multiply = new int[n + 1];
        multiply[0] = 0;
        multiply[1] = 1;
        multiply[2] = 2;
        multiply[3] = 3;
        for (int i = 4; i <= n; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int current = multiply[j] * multiply[i - j];
                if (max < current)
                    max = current;
                multiply[i] = max;
            }
        }
        max = multiply[n];
        return max;
    }

    /**
     * 解法二 贪婪算法
     *
     * @param n
     * @return
     */
    public static int cutRope2(int n) {
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        int timeOf3 = n / 3;
        if (n - timeOf3 * 3 == 1)
            timeOf3--;
        int timeOf2 = (n - timeOf3 * 3) / 2;
        return (int) Math.pow(3, timeOf3) * (int) Math.pow(2, timeOf2);
    }

    /**
     * 第一个不重复的字符
     *
     * @param str
     * @return
     */
    public static int FirstNotRepeatingChar(String str) {
        int[] table = new int[256];
        char[] chars = str.toCharArray();
        for (char c : chars)
            table[c]++;
        for (int i = 0; i < chars.length; i++)
            if (table[chars[i]] == 1)
                return i;
        return -1;
    }

    /**
     * No.15 二进制中1的个数
     *
     * @param n
     * @return
     */
    public static int numberOfOne(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0)
                count++;
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 解法二 位运算
     *
     * @param n
     * @return
     */
    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 2的整数次方
     *
     * @param n
     * @return
     */
    public static boolean isPowerOf2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
            if (count > 1)
                return false;
        }
        return count == 1;
    }

    /**
     * 二进制下m到n变化的位数
     *
     * @param m
     * @param n
     * @return
     */
    public static int mTon(int m, int n) {
        int num = m ^ n;
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * Excel列号编码
     *
     * @param s
     * @return
     */
    public static int coloum(String s) {
        int[] table = new int[256];
        for (int i = 'A'; i <= 'Z'; i++) {
            table[i] = i - 'A' + 1;
        }
        char[] t = s.toCharArray();
        int num = 0;
        for (char c : t) {
            int n = table[c];
            num = num * 26 + n;
        }
        return num;
    }

    /**
     * No.16 数值的正数次方
     *
     * @param m
     * @param n
     * @return
     */
    public static double power1(double m, int n) {
        double res = 1;
        int abs = n;
        if (m == 0)
            return 0;
        if (n < 0)
            abs = -n;
        for (int i = 1; i <= abs; i++) {
            res *= m;
        }
        return n < 0 ? (1.0 / res) : res;
    }

    /**
     * 递归
     *
     * @param m
     * @param n
     * @return
     */
    public static double power2(double m, int n) {
        if (n == 0)
            return 1;
        if (m == 0)
            return 0;
        double res = power2(m, Math.abs(n) >> 1);
        res *= res;
        if ((n & 1) == 1)
            res *= m;
        return n < 0 ? (1.0 / res) : res;
    }

    /**
     * No.17 打印从1到最大的n位数
     *
     * @param n
     */
    public static void printToN1(int n) {
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        while (!increment(number)) {
            printNumber(number);
        }
    }

    public static boolean increment(char[] number) {
        //溢出
        boolean isOver = false;
        //进位
        int carry = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            int num = number[i] - '0' + carry;
            if (i == number.length - 1)
                num++;
            if (num >= 10) {
                if (i == 0)
                    isOver = true;
                else {
                    number[i] = '0';
                    carry = 1;
                }
            } else {
                number[i] = (char) (num + 48);
                break;
            }
        }
        return isOver;
    }

    public static void printNumber(char[] number) {
        boolean isBeginning0 = true;
        for (int i = 0; i < number.length; i++) {
            //从第一个不为0的开始打印到末尾
            if (isBeginning0 && number[i] != '0')
                isBeginning0 = false;
            if (!isBeginning0)
                System.out.print(number[i]);
        }
        System.out.println();
    }

    /**
     * 解法二：全排列
     *
     * @param n
     */
    public static void printToN2(int n) {

    }

    /**
     * No.18 删除指定节点
     *
     * @param head
     * @param node
     * @return
     */
    public static ListNode deleteNode(ListNode head, ListNode node) {
        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        } else {
            if (node == head) {
                return null;
            } else {
                ListNode current = head;
                while (current.next != node) {
                    current = current.next;
                }
                current.next = null;
            }
        }
        return head;
    }

    /**
     * 删除重复节点（有序链表）
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicateNode(ListNode head) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode pre = preHead;
        ListNode cur = preHead;
        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                cur = cur.next;
            } else {
                while (pre.next.val != cur.val)
                    pre = pre.next;
                while (cur.next != null && cur.val == cur.next.val)
                    cur = cur.next;
                cur = cur.next;
                pre.next = cur;
            }
        }
        return preHead.next;

    }

    /**
     * 别人写得好系列  ╮( •́ω•̀ )╭
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        // 只有0个或1个结点，则返回
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        // 当前结点是重复结点
        if (pHead.val == pHead.next.val) {
            ListNode pNode = pHead.next;
            // 跳过值与当前结点相同的全部结点,找到第一个与当前结点不同的结点
            while (pNode != null && pNode.val == pHead.val) {
                pNode = pNode.next;
            }
            // 从第一个与当前结点不同的结点开始递归
            return deleteDuplication(pNode);
        }
        // 当前结点不是重复结点
        else {
            // 保留当前结点，从下一个结点开始递归
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    /**
     * No.19 正则表达式匹配
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean match(char[] str, char[] pattern) {
        //return new String(str).matches(new String(pattern));
        if (str == null || pattern == null)
            return false;
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    private static boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {

        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length)
            return true;

        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length)
            return false;

        //模式第2个是*
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            //字符串第1个跟模式第1个不匹配
            if (strIndex == str.length || (str[strIndex] != pattern[patternIndex] && pattern[patternIndex] != '.')) {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
            //字符串第1个跟模式第1个匹配,分3种匹配模式
            else {
                return matchCore(str, strIndex + 1, pattern, patternIndex + 2) ||  //视为模式匹配1个字符
                        matchCore(str, strIndex, pattern, patternIndex + 2) ||            //模式后移2，视为x*匹配0个字符
                        matchCore(str, strIndex + 1, pattern, patternIndex);                //*匹配1个，再匹配str中的下一个
            }
        } else if (strIndex == str.length)
            return false;
            //模式第2个不是*，且字符串第1个跟模式第1个匹配
        else if (str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.')
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        return false;
    }

    /**
     * No.20 表示数值的字符串
     * A[.[B]][e|EC]或.B[e|EC]
     * A——整数部分 B——小数部分 C——指数部分
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(char[] str) {
        int eCount = 0;
        int point = 0;
        int index = 0;
        if (str[0] == '+' || str[0] == '-') {
            index++;
        }
        for (int i = index; i < str.length; i++) {
            if (str[i] == '+' || str[i] == '-') {
                if (str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false;
                continue;
            }
            if (str[i] == 'e' || str[i] == 'E') {
                eCount++;
                if (eCount > 1)
                    return false;
                if (i == 0 || i == str.length - 1 || str[i - 1] < 48 || str[i - 1] > 57)
                    return false;
                point++;
                continue;
            }
            if (str[i] == '.') {
                point++;
                if (point > 1)
                    return false;
                continue;
            }
            if (str[i] < 48 || str[i] > 57)
                return false;
        }
        return true;
    }

    /**
     * No.21 调整数组顺序使奇数在前偶数在后
     *
     * @param array
     */
    public static void reOrderArray1(int[] array) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            while (j > i && array[j] % 2 == 0)
                j--;
            while (i < j && array[i] % 2 != 0)
                i++;
            if (i < j) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
    }

    /**
     * 奇数和奇数，偶数和偶数之间的相对位置不变
     *
     * @param array
     */
    public static void reOrderArray2(int[] array) {
        for (int i = 1; i < array.length; i++)
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 != 0) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
    }

    /**
     * 奇数和奇数，偶数和偶数之间的相对位置不变
     *
     * @param array
     */
    public static void reOrderArray3(int[] array) {
        int numOfOdd = 0;
        int index = 0;
        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++)
            if (array[i] % 2 != 0)
                numOfOdd++;

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0)
                temp[index++] = array[i];
            else
                temp[numOfOdd++] = array[i];
        }

        for (int i = 0; i < temp.length; i++)
            array[i] = temp[i];
    }

    /**
     * No.22 链表中倒数第K个节点
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail1(ListNode head, int k) {
        if (k <= 0 || head == null)
            return null;
        ListNode first = head;
        ListNode second = head;
        while (k >= 1) {
            if (first != null)
                first = first.next;
            else
                break;
            k--;
        }
        if (k > 0)
            return null;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail2(ListNode head, int k) {

        ListNode first = head;
        ListNode second = head;
        int i = 0;
        for (; first != null; i++) {
            if (i >= k)
                second = second.next;
            first = first.next;
        }
        return i < k ? null : second;
    }

    /**
     * No.23 链表中环的入口节点
     *
     * @param pHead
     * @return
     */
    public static ListNode EntryNodeOfLoop1(ListNode pHead) {
        HashSet<ListNode> set = new HashSet<>();
        while (pHead != null) {
            if (set.contains(pHead))
                return pHead;
            set.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * 解法二
     * 快慢指针
     *
     * @param pHead
     * @return
     */
    public static ListNode EntryNodeOfLoop2(ListNode pHead) {
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
            if (slow == fast)
                break;
        }
        if (fast == null)
            return null;
        while (slow != pHead) {
            slow = slow.next;
            pHead = pHead.next;
        }
        return pHead;

    }

    /**
     * No.24 反转链表
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        ListNode preHead = new ListNode(-1);
        ListNode node = null;
        while (head != null) {
            node = head;
            head = head.next;
            node.next = null;
            node.next = preHead.next;
            preHead.next = node;
        }

        return preHead.next;
    }

    /**
     * No.25 合并有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge1(ListNode list1, ListNode list2) {

        ListNode preHead = new ListNode(-1);
        ListNode current = preHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        }

        if (list2 != null) {
            current.next = list2;
        }

        return preHead.next;
    }

    /**
     * 解法二
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge2(ListNode list1, ListNode list2) {

        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        ListNode curMergeHead = null;
        if (list1.val < list2.val) {
            curMergeHead = list1;
            curMergeHead.next = Merge2(list1.next, list2);
        } else {
            curMergeHead = list2;
            curMergeHead.next = Merge2(list1, list2.next);
        }
        return curMergeHead;
    }

    /**
     * No.26 树的子结构
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean res = false;
        if (root1 != null && root2 != null) {
            //start
            if (root1.val == root2.val)
                res = subTree(root1, root2);
            if (!res)
                res = HasSubtree(root1.left, root2);
            if (!res)
                res = HasSubtree(root1.right, root2);
        }
        return res;
    }

    private static boolean subTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null)
            return false;
        if (root2 == null)
            return true;
        if (root1.val != root2.val)
            return false;
        return subTree(root1.left, root2.left) && subTree(root1.right, root2.right);
    }

    /**
     * No.27 二叉树的镜像
     *
     * @param root
     */
    public static void mirror(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;

        TreeNode node = root.right;
        root.right = root.left;
        root.left = node;

        mirror(root.left);
        mirror(root.right);
    }

    /**
     * No.28 对称的二叉树
     *
     * @return
     */
    public static boolean isSymmetrical(TreeNode root) {
        if (root == null)
            return true;
        TreeNode left = root.left;
        TreeNode right = root.right;
        return isSymmetrical(left, right);
    }

    private static boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val != right.val)
            return false;
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    /**
     * No.29 顺时针打印矩阵
     *
     * @param matrix
     */
    public static void printByCircle(int[][] matrix) {
        //左上角和右下角
        int topX = 0, topY = 0;
        int botX = matrix.length - 1, botY = matrix[0].length - 1;
        while (topX <= botX && topY <= botY) {
            int i = topX, j = topY;

            while (j < botY)
                System.out.print(matrix[i][j++]);

            while (i < botX)
                System.out.print(matrix[i++][j]);

            while (j > topY)
                System.out.print(matrix[i][j--]);

            while (i > topX)
                System.out.print(matrix[i--][j]);

            topX++;
            topY++;
            botX--;
            botY--;
        }

    }

    /**
     * No.30 包含min函数的栈
     */
    private class Solution {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> min = new Stack<>();

        public void push(int node) {
            stack.push(node);
            if (min.isEmpty())
                min.push(node);
            else {
                if (node < min.peek())
                    min.push(node);
                else
                    min.push(min.peek());
            }
        }

        public void pop() {
            stack.pop();
            min.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min.peek();
        }
    }

    /**
     * No.31 栈的压入、弹出序列
     * 辅助栈
     *
     * @param inOrder
     * @param popOrder
     * @return
     */
    public static boolean IsPopOrder(int[] inOrder, int[] popOrder) {
        boolean res = false;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < popOrder.length; i++) {
            while (stack.isEmpty() || stack.peek() != popOrder[i]) {
                if (j == inOrder.length)
                    break;
                stack.push(inOrder[j]);
                j++;
            }
            if (stack.peek() != popOrder[i])
                break;
            stack.pop();
            if (stack.isEmpty() && i == popOrder.length - 1)
                res = true;
        }
        return res;
    }

    /**
     * No.32 从上到下打印二叉树（不分行）
     *
     * @param root
     */
    public static void printBylayer1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    /**
     * 分行打印
     *
     * @param root
     */
    public static void printBylayer2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int num = queue.size();
            while (num-- > 0) {
                TreeNode node = queue.poll();
                System.out.print(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            System.out.println();
        }
    }

    /**
     * 之字形打印
     *
     * @param root
     */
    public static void printBylayer3(TreeNode root) {
        Stack[] stacks = new Stack[2];
        stacks[0] = new Stack();
        stacks[1] = new Stack();
        int current = 0;
        int next = 1;
        stacks[current].push(root);
        while (!stacks[0].isEmpty() || !stacks[1].isEmpty()) {
            TreeNode node = (TreeNode) stacks[current].pop();
            System.out.print(node.val);
            if (current == 0) {
                if (node.left != null)
                    stacks[next].push(node.left);
                if (node.right != null)
                    stacks[next].push(node.right);
            } else {
                if (node.right != null)
                    stacks[next].push(node.right);
                if (node.left != null)
                    stacks[next].push(node.left);
            }
            if (stacks[current].isEmpty()) {
                current = 1 - current;
                next = 1 - next;
                System.out.println();
            }
        }
    }

    /**
     * No.33 二叉搜索树后序遍历序列
     *
     * @param num
     * @param start
     * @param end
     * @return
     */
    public static boolean verifyBSTPost(int[] num, int start, int end) {

        int root = num[end];
        int i = start;
        //分割点
        for (; i < end; i++) {
            if (num[i] > root)
                break;
        }
        int j = i;
        for (; j < end; j++)
            if (num[j] < root)
                return false;

        boolean left = true;
        if (i > start)
            left = verifyBSTPost(num, start, i - 1);

        boolean right = true;
        if (i < end)
            right = verifyBSTPost(num, i, end - 1);

        return left && right;
    }

    /**
     * 二叉搜索数前序遍历序列
     *
     * @param num
     * @param start
     * @param end
     * @return
     */
    public static boolean verifyBSTPre(int[] num, int start, int end) {
        int root = num[start];
        int i = end;
        for (; i > start; i--)
            if (num[i] < root)
                break;
        int j = i;
        for (; j > start; j--)
            if (num[j] > root)
                return false;

        boolean left = true;
        if (i > start)
            left = verifyBSTPre(num, start + 1, i);
        boolean right = true;
        if (i < end)
            right = verifyBSTPre(num, i + 1, end);

        return left && right;
    }

    /**
     * No.34 二叉树中和为某一值的路径
     *
     * @param root
     * @param target
     * @return
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<Integer> lt = new ArrayList<>();
        findPath(root, target, lt);
        return res;
    }

    private void findPath(TreeNode root, int target, ArrayList<Integer> lt) {
        if (root == null)
            return;

        lt.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            res.add(new ArrayList<>(lt));

        findPath(root.left, target, lt);
        findPath(root.right, target, lt);
        lt.remove(lt.size() - 1);
    }

    /**
     * No.35 复杂链表复制
     *
     * @param head
     * @return
     */
    public static RandomListNode cloneList(RandomListNode head) {
        if (head == null)
            return null;
        //复制next节点
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode node = new RandomListNode(cur.label);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }

        //复制pre节点
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                RandomListNode next = cur.next;
                next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        //新链表
        cur = head;
        RandomListNode newHead = head.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }

        return newHead;
    }

    /**
     * No.36 二叉树转为链表
     *
     * @param node
     * @return
     */
    private static TreeNode head = null;
    private static TreeNode last = null;

    public static TreeNode getTreeToList(TreeNode node) {
        Inorder(node);
        return head;
    }

    private static void Inorder(TreeNode node) {
        if (node == null)
            return;
        Inorder(node.left);
        if (last == null) {
            head = node;
            last = node;
        } else {
            last.right = node;
            node.left = last;
            last = node;
        }
        Inorder(node.right);
    }

    /**
     * No.37 序列化二叉树
     *
     * @param node
     * @return
     */
    //前序遍历
    public static String serialize(TreeNode node) {
        if (node == null)
            return "#!";
        String s = node.val + "!";
        s += serialize(node.left);
        s += serialize(node.right);
        return s;
    }

    /**
     * 反序列化二叉树
     *
     * @param s
     * @return
     */
    public static TreeNode deSerialize(String s) {
        String[] ss = s.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String sss : ss)
            queue.offer(sss);
        return recover(queue);
    }

    private static TreeNode recover(Queue<String> queue) {
        String s = queue.poll();
        if (s == "#")
            return null;
        TreeNode node = new TreeNode(Integer.valueOf(s));
        node.left = recover(queue);
        node.right = recover(queue);
        return node;
    }

    /**
     * No.38 字符串排列 （字典序、重复字符）
     * 递归
     *
     * @param str
     * @return
     */
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> lt = new ArrayList<>();
        if (str != null && str.length() > 0) {
            permutation(str.toCharArray(), 0, lt);
            Collections.sort(lt);
        }
        return lt;
    }

    private static void permutation(char[] chars, int start, ArrayList<String> lt) {
        if (start == chars.length) {
            String s = new String(chars);
            /**去重*/
            if (!lt.contains(s))
                lt.add(s);
        } else {
            for (int i = start; i < chars.length; i++) {
                swap(chars, start, i);
                permutation(chars, start + 1, lt);
                swap(chars, start, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    /**
     * 字符的组合
     * 解法一：位图
     *
     * @param s
     * @return
     */
    public static ArrayList<String> Combination(String s) {
        ArrayList<String> lt = new ArrayList<>();
        if (s != null && s.length() > 0) {
            int len = s.length();
            int n = 1 << len;
            //2^n-1种组合
            for (int i = 1; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < len; j++) {
                    if ((i & (1 << j)) != 0) {
                        sb.append(s.charAt(j));
                    }
                }
                if (!lt.contains(sb.toString()))
                    lt.add(sb.toString());
            }
        }
        return lt;
    }

    /**
     * 解法二：递归
     *
     * @param s
     * @return
     */
    public static ArrayList<String> getCombination(String s) {
        ArrayList<String> lt = new ArrayList<>();
        if (s != null && s.length() > 0) {
            for (int i = 1; i <= s.length(); i++) {
                combination(s, "", i, lt);
            }
        }
        return lt;
    }

    public static void combination(String s, String ss, int num, ArrayList<String> lt) {
        if (num == 0) {
            lt.add(ss);
        }
        if (s.length() != 0 && num > 0) {
            ss += s.substring(0, 1);
            combination(s.substring(1), ss, num - 1, lt);
            ss = ss.substring(0, ss.length() - 1);
            combination(s.substring(1), ss, num, lt);
        }
    }

    /**
     * 正方体8个顶点
     *
     * @param nums
     * @return
     */
    public static ArrayList<String> getEightPoints(int[] nums) {
        ArrayList<String> lt = new ArrayList<>();
        eightPoints(nums, 0, lt);
        return lt;
    }

    private static void eightPoints(int[] nums, int start, ArrayList<String> lt) {
        if (start == nums.length) {
            if (check(nums)) {
                String s = "";
                for (int i = 0; i < nums.length; i++) {
                    s += nums[i];
                }
                if (!lt.contains(s))
                    lt.add(s);
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                eightPoints(nums, start + 1, lt);
                swap(nums, start, i);
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static boolean check(int[] nums) {
        if (nums[0] + nums[1] + nums[2] + nums[3] == nums[4] + nums[5] + nums[6] + nums[7] &&
                nums[0] + nums[2] + nums[4] + nums[6] == nums[1] + nums[3] + nums[5] + nums[7] &&
                nums[0] + nums[1] + nums[4] + nums[5] == nums[2] + nums[3] + nums[6] + nums[7])
            return true;
        return false;
    }

    /**
     * 八皇后问题
     * 任意两个皇后不在同一行、同一列、同一对角线
     * 数组下标——行   元素——列
     *
     * @param n
     * @return
     */
    public static int queens(int n) {
        ArrayList<String> lt = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        getNumbers(nums, 0, lt);
        return lt.size();
    }

    private static void getNumbers(int[] nums, int start, ArrayList<String> lt) {
        if (start == nums.length) {
            String s = "";
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; i != j && j < nums.length; j++) {
                    if ((i - j) == nums[i] - nums[j] || (i - j) == nums[j] - nums[i])
                        return;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                s += nums[i];
            }
            lt.add(s);
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                getNumbers(nums, start + 1, lt);
                swap(nums, start, i);
            }
        }
    }

    /**
     * No.39 数组中出现次数超过一半的数
     *
     * @param nums
     * @return
     */
    public static int getMoreThanHalf(int[] nums) {
        int num = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                num = nums[i];
                count++;
            } else {
                if (nums[i] != num)
                    count--;
                else
                    count++;
            }
        }
        count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num)
                count++;
        }
        return count > nums.length / 2 ? num : 0;
    }

    /**
     * 解法二
     * 中位数（下标n/2处为目标数）
     *
     * @param nums
     * @return
     */
    public static int moreThanHalf(int[] nums) {
        int middle = nums.length >> 1;
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        while (index != middle) {
            if (index > middle) {
                end = index - 1;
                index = partition(nums, start, end);
            }
            if (index < middle) {
                start = index + 1;
                index = partition(nums, start, end);
            }
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[index])
                count++;
        }
        return count > middle ? nums[index] : 0;
    }

    private static int partition(int[] nums, int start, int end) {
        int point = nums[start];
        while (start < end) {
            while (end > start && nums[end] >= point)
                end--;
            nums[start] = nums[end];
            while (start < end && nums[start] <= point)
                start++;
            nums[end] = nums[start];
        }
        nums[start] = point;
        return start;
    }

    /**
     * No.40 最小的k个数
     *
     * @param k
     * @param num
     * @return
     */
    public static ArrayList<Integer> getLeastK(int k, int[] num) {

        /**排序*/
//        ArrayList<Integer> lt = new ArrayList<>();
//        if(k<0||k>num.length)
//            return null;
//        Arrays.sort(num);
//        for(int i=0;i<k;i++)
//            lt.add(num[i]);
//        return lt;

        /**解法二 最小堆*/
        ArrayList<Integer> lt = new ArrayList<>();
        if (k < 1 || k > num.length)
            return lt;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int n : num) {
            minHeap.offer(n);
        }
        while (k-- > 0) {
            lt.add(minHeap.poll());
        }
        return lt;
    }

    /**
     * No.41 数据流中的中位数
     * 最大堆和最小堆
     * PriorityQueue 优先队列
     */
    static class DynamicArray {
//        方法一
//        private static ArrayList<Integer> lt = new ArrayList<>();
//        public static void insert(int num){
//            lt.add(num);
//        }
//        public static double getMedian(){
//            int size = lt.size();
//            if(size%2!=0)
//                return lt.get(size/2)*1.0;
//            else
//                return (lt.get(size/2)+lt.get(size/2-1))*0.5;
//        }

        //        方法二
        static int count;
        static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        public static void insert(int num) {
            count++;
            if ((count & 1) == 0)//偶数
            {
                if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                    maxHeap.offer(num);
                    num = maxHeap.poll();
                }
                minHeap.offer(num);
            } else {
                if (!minHeap.isEmpty() && num > minHeap.peek()) {
                    minHeap.offer(num);
                    num = minHeap.poll();
                }
                maxHeap.offer(num);
            }
        }

        public static double getMid() {
            if ((count & 1) == 0) {
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek();
            }
        }

    }

    /**
     * No.42 连续子数组的最大和
     * 局部和 与 全局最大值
     *
     * @param num
     * @return
     */
    public static int findGreatestSumOfSubArray1(int[] num) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
            max = Math.max(sum, max);
            if (sum < 0)
                sum = 0;
        }
        return max;
    }

    /**
     * No.42 解法二
     * 动态规划
     *
     * @param num
     * @return
     */
    public static int findGreatestSumOfSubArray2(int[] num) {
        int[] dp = new int[num.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < num.length; i++) {
            if (i == 0 || dp[i - 1] < 0) {
                dp[i] = num[i];
            } else {
                dp[i] = dp[i - 1] + num[i];
            }

            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * No.43(1-n)正数中1出现的次数
     *
     * @param n
     * @return
     */
    public static int countOfOne(int n) {
        int num = 1;
        for (int i = 2; i <= n; i++) {
            String s = i + "";
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1')
                    num++;
            }
        }
        return num;
    }

    /**
     * No.44数字序列中某一位数字
     *
     * @param index
     * @return
     */
    public static int digitAtIndex(int index) {
        if (index < 0)
            return -1;
        int digits = 1;
        while (true) {
            int numbers = countOfIntegers(digits);
            /**确定区间*/
            if (index < numbers * digits)
                return digitAtIndex(index, digits);
            index -= digits * numbers;
            digits++;
        }
    }

    /**
     * 统计某位数的数量
     * 一位数 0-9  10
     * 两位数 10-99 90
     * 三位数 100-999 900
     */
    private static int countOfIntegers(int digits) {
        if (digits == 1)
            return 10;
        int count = (int) Math.pow(10, digits - 1);
        return 9 * count;
    }

    /**
     * 在某个区间寻找
     */
    private static int digitAtIndex(int index, int digits) {
        int numbers = beginNumber(digits) + index / digits;
        int indexFromRight = digits - index % digits;
        for (int i = 1; i < indexFromRight; i++)
            numbers /= 10;
        return numbers % 10;
    }

    private static int beginNumber(int digits) {
        if (digits == 1)
            return 0;
        return (int) Math.pow(10, digits - 1);
    }

    /**
     * No.45把数组排成最小的数
     * 自定义比较器
     *
     * @param numbers
     * @return
     */
    public static String PrintMinNumber(String[] numbers) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(numbers, new MyComparator());
        for (String s : numbers)
            sb.append(s);
        return sb.toString();

    }

    private static class MyComparator implements Comparator<String> {
        /**
         * 返回值
         * 等于0，则o1等于o2;
         * 大于0，则o1大于o2;
         * 小于0，则o1小于o2.
         *
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(String o1, String o2) {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s1.compareTo(s2);
        }
    }

    /**
     * No.46把数字翻译成字符串
     *
     * @param num
     * @return
     */
    public static int getTranslationCount1(int num) {
        String s = num + "";
        counter(0, s.length(), s, "");
        return set.size();
    }

    public static void counter(int start, int end, String num, String res) {
        if (start == end) {
            set.add(res);
        }
        for (int i = start + 1; i <= end; i++) {
            String s = num.substring(start, i);
            int n = Integer.valueOf(s);
            if (n <= 25) {
                res += tab[n];
                counter(i, end, num, res);
                res = res.substring(0, res.length() - 1);
            }
        }
    }

    /**
     * No.46 解法二
     * 动态规划
     *
     * @param s
     * @return
     */
    public static int getTranslationCount2(String s) {
        int[] dp = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            int count = 0;
            if (i < s.length() - 1)
                count += dp[i + 1];
            else
                count = 1;
            if (i < s.length() - 1) {
                String s1 = s.substring(i, i + 1);
                String s2 = s.substring(i + 1, i + 2);
                int num = Integer.valueOf(s1 + s2);
                if (num >= 10 && num <= 25) {
                    if (i < s.length() - 2) {
                        count += dp[i + 2];
                    } else {
                        count += 1;
                    }
                }
            }
            dp[i] = count;
        }
        return dp[0];
    }

    /**
     * No.47礼物最大值
     * 动态规划
     * dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + value[i][j];
     *
     * @param value
     * @return
     */
    public static int getMaxValue1(int[][] value) {
        int row = value.length;
        int col = value[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = value[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] = value[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = value[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + value[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * No.47plus
     * 空间优化
     * 一维数组
     *
     * @param value
     * @return
     */
    public static int getMaxValue2(int[][] value) {
        int row = value.length;
        int col = value[0].length;
        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int left = 0;
                int up = 0;
                if (i > 0)
                    up = dp[j];
                if (j > 0)
                    left = dp[j - 1];
                dp[j] = Math.max(left, up) + value[i][j];
            }
        }
        return dp[col - 1];
    }

    /**
     * No.48最长不含重复字符的子字符串
     * 动态规划
     * dp[i]---以第 i 个字符结尾的最长不含重复子串长度
     *
     * @param s
     * @return
     */
    public static int getMaxNotRepeatedSubString(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] table = new int[256];
        int[] dp = new int[s.length()];
        dp[0] = 1;
        table[s.charAt(0)] = 0;
        int max = dp[0];
        for (int i = 1; i < s.length(); i++) {
            if (table[s.charAt(i)] == 0 && s.charAt(i) != s.charAt(0)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int d = i - table[s.charAt(i)];
                if (d > dp[i - 1])
                    dp[i] = dp[i - 1] + 1;
                else
                    dp[i] = d;
            }
            table[s.charAt(i)] = i;
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * No.49丑数
     * m是n的因子，即 n % m == 0
     *
     * @param index
     * @return
     */
    public static int getUglyNumber(int index) {
        if (index == 0)
            return 0;
        ArrayList<Integer> lt = new ArrayList<>();
        lt.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (lt.size() < index) {
            int num2 = lt.get(i2) * 2;
            int num3 = lt.get(i3) * 3;
            int num5 = lt.get(i5) * 5;
            int min = Math.min(Math.min(num2, num3), num5);
            lt.add(min);
            if (min == num2)
                i2++;
            if (min == num3)
                i3++;
            if (min == num5)
                i5++;
        }
        return lt.get(lt.size() - 1);
    }

    /**
     * No.50第一个只出现一次的字符
     *
     * @param s
     * @return
     */
    public static char firstNotRepeatingChar(String s) {
        int[] table = new int[256];
        for (char c : s.toCharArray()) {
            table[c]++;
        }
        for (char c : s.toCharArray()) {
            if (table[c] == 1)
                return c;
        }
        return ' ';
    }

    /**
     * No.50plus 从第一个字符串中删除第二个字符串中出现的所有字符
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String deleteChar(String s1, String s2) {
        String s = "";
        int[] table = new int[256];
        for (char c : s2.toCharArray()) {
            table[c]++;
        }
        for (char c : s1.toCharArray()) {
            if (table[c] == 0)
                s += String.valueOf(c);
        }
        return s;
    }

    /**
     * No.50plus 删除字符中所有重复的字符
     *
     * @param s
     * @return
     */
    public static String deleteRepeatingChar(String s) {
        String res = "";
        boolean[] table = new boolean[256];
        for (char c : s.toCharArray()) {
            if (table[c] == true)
                continue;
            table[c] = true;
            res += String.valueOf(c);
        }
        return res;
    }

    /**
     * No.50plus 是否为互变词
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] table = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i)]++;
            table[s2.charAt(i)]--;
        }
        for (int n : table)
            if (n != 0)
                return false;
        return true;
    }

    /**
     * No.51数组中的逆序对
     * 归并思想
     *
     * @param nums
     * @return
     */
    public static int inversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(0, nums.length - 1, temp, nums);
        return countOfPairs;
    }

    public static void mergeSort(int begin, int end, int[] temp, int[] nums) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            mergeSort(begin, mid, temp, nums);
            mergeSort(mid + 1, end, temp, nums);
            merge(begin, mid, end, temp, nums);
        }
    }

    public static void merge(int begin, int mid, int end, int[] temp, int[] nums) {
        int left = mid;
        int right = end;
        int len = end - begin + 1;
        while (left >= begin && right > mid) {
            if (nums[left] > nums[right]) {
                countOfPairs += (right - mid);
                temp[end--] = nums[left--];
            } else {
                temp[end--] = nums[right--];
            }
        }
        while (left >= begin)
            temp[end--] = nums[left--];
        while (right > mid)
            temp[end--] = nums[right--];
        for (int i = 0; i < len; i++) {
            nums[begin] = temp[begin];
            begin++;
        }
    }

    /**
     * No.52两个链表的第一个公共节点
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        while (pHead1 != null) {
            s1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            s2.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode node1 = null, node2 = null;
        while (!s1.isEmpty() && !s2.isEmpty() && s1.peek() == s2.peek()) {
            node1 = s1.pop();
            node2 = s2.pop();
        }
        return node1;
    }

    /**
     * No.53数字k出现的次数
     * 排序数组---二分法
     *
     * @param array
     * @param k
     * @return
     */
    public static int getNumberOfK(int[] array, int k) {
        int first = getFirstK(array, 0, array.length - 1, k);
        int last = getLastK(array, 0, array.length - 1, k);
        return first == -1 ? 0 : (last - first + 1);
    }

    //第一个K的下标
    private static int getFirstK(int[] array, int begin, int end, int k) {
        if (begin > end)
            return -1;
        int mid = (begin + end) / 2;
        if (array[mid] == k) {
            if ((mid > 0 && array[mid - 1] != k) || mid == 0)
                return mid;
            else
                end = mid - 1;
        } else if (array[mid] < k) {
            begin = mid + 1;
        } else {
            end = mid - 1;
        }
        return getFirstK(array, begin, end, k);
    }

    //最后一个K的下标
    private static int getLastK(int[] array, int begin, int end, int k) {
        if (begin > end)
            return -1;
        int mid = (begin + end) / 2;
        if (array[mid] == k) {
            if ((mid < array.length - 1 && array[mid + 1] != k) || mid == array.length - 1)
                return mid;
            else
                begin = mid + 1;
        } else if (array[mid] < k) {
            begin = mid + 1;
        } else {
            end = mid - 1;
        }
        return getLastK(array, begin, end, k);
    }

    /**
     * No.53plus 数组中缺失的数
     * 第一个与数组下标不相等的元素
     *
     * @param array
     * @return
     */
    public static int getMissingNumber(int[] array) {
        int begin = 0, end = array.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (array[mid] != mid) {
                if (mid == 0 || array[mid - 1] == mid - 1)
                    return mid;
                else
                    end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return array.length;
    }

    /**
     * No.53plusplus 数组中数值和下标相等的元素
     * 二分法
     *
     * @param array
     * @return
     */
    public static int getNumberSameAsIndex(int[] array) {
        int begin = 0, end = array.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (array[mid] == mid)
                return mid;
            else if (array[mid] < mid) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * No.54二叉搜索树中第K大的节点
     * 中序遍历（有序）
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node == null)
            return;
        inOrder(node.left);
        nodes.add(node);
        inOrder(node.right);
    }

    /**
     * No.55二叉树的深度
     * 从下至上
     *
     * @param root
     * @return
     */
    public static int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int depth = Math.max(TreeDepth(root.left), TreeDepth(root.right));
        return depth + 1;
    }

    /**
     * No.55plus  平衡二叉树
     * 解法一
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int dif = left - right;
        if (dif > 1 || dif < -1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 解法二
     *
     * @param root
     * @return
     */
    private static int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        int dif = left - right;
        if (dif > 1 || dif < -1) {
            isBalanced = false;
        }
        return left > right ? left + 1 : right + 1;
    }

    /**
     * No.56数组中只出现一次的两个数字
     * 解法一
     *
     * @param num
     * @return
     */
    public static int[] onlyTwice1(int[] num) {
        int[] res = new int[2];
        int index = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i]))
                map.put(num[i], 0);
            else
                map.put(num[i], 1);
        }
        for (int n : map.keySet()) {
            if (map.get(n) == 1)
                res[index++] = n;
        }
        return res;
    }

    /**
     * 解法二
     *
     * @param num
     * @return
     */
    public static int[] onlyTwice2(int[] num) {
        int[] res = new int[2];
        int n = 0;
        int bit = 0;
        int num1 = 0, num2 = 0;
        //异或
        for (int i = 0; i < num.length; i++) {
            n ^= num[i];
        }
        while ((n & 1) == 0) {
            n = n >> 1;
            bit++;
        }
        for (int i = 0; i < num.length; i++) {
            if (isOne(num[i], bit))
                num1 ^= num[i];
            else
                num2 ^= num[i];
        }
        res[0] = num1;
        res[1] = num2;
        return res;
    }

    private static boolean isOne(int num, int bit) {
        return ((num >> bit) & 1) == 1 ? true : false;
    }

    /**
     * No.56plus
     * 只出现一次的数字（其他数字出现3次）
     * 位数组
     *
     * @param num
     * @return
     */
    public static int onlyOnce(int[] num) {
        //高位在前，低位在后
        int[] bitArray = new int[32];
        for (int i = 0; i < num.length; i++) {
            int bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                int bit = num[i] & bitMask;
                if (bit != 0)
                    bitArray[j] += 1;
                bitMask = bitMask << 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res += bitArray[i] % 3;
        }
        return res;
    }

    /**
     * No.57和为target的两个数
     * 解法一
     *
     * @param num
     * @param target
     * @return
     */
    public static int[] sumOfTwo(int[] num, int target) {
        int[] number = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            int remain = target - num[i];
            if (map.containsKey(remain)) {
                number[0] = remain;
                number[1] = num[i];
                return number;
            }
            map.put(num[i], i);
        }
        return number;
    }

    /**
     * 解法二  双指针
     *
     * @param num
     * @param target
     * @return
     */
    public static int[] sumOftwo(int[] num, int target) {
        int first = 0;
        int last = num.length - 1;
        int[] index = new int[2];
        while (first < last) {
            int sum = num[first] + num[last];
            if (sum == target) {
                index[0] = first;
                index[1] = last;
                break;
            } else if (sum > target) {
                last--;
            } else {
                first++;
            }
        }
        return index;
    }

    /**
     * No.57plus
     * 和为s的连续正数序列
     *
     * @param m
     * @param n
     * @return
     */
    public static ArrayList<ArrayList<Integer>> sumOfSeq(int m, int n) {
        int first = 1;
        int last = 2;
        int curSum = 3;
        ArrayList<Integer> lt = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        while (first < last && last <= m) {
            if (curSum == n) {
                lt.add(first);
                lt.add(last);
                res.add(new ArrayList<>(lt));
                lt.clear();
                last++;
                curSum += last;
            } else if (curSum < n) {
                last++;
                curSum += last;
            } else {
                curSum -= first;
                first++;
            }
        }
        return res;
    }

    /**
     * No.58翻转字符串
     * 解法一
     *
     * @param s
     * @return
     */
    public static String reverse1(String s) {
        String[] str = s.split(" ");
        String res = "";
        for (int i = str.length - 1; i > 0; i--)
            res += str[i] + " ";
        res += str[0];
        return res;
    }

    /**
     * 翻转字符串
     * 解法二
     * 先翻转整个句子，再翻转每个单词
     *
     * @param s
     * @return
     */
    public static String reverse2(String s) {
        String res = reverse(s);

        return res;
    }

    public static String reverse(String s) {
        String str = "";
        for (int i = s.length() - 1; i >= 0; i--)
            str += s.charAt(i);
        return str;
    }

    /**
     * No.58plus
     * 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public static String leftReverse(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    /**
     * No.59滑动窗口最大值
     * 双端队列模拟窗口
     *
     * @param num
     * @param n
     * @return
     */
    public static ArrayList maxWindow(int[] num, int n) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        //初始窗口
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && num[i] >= num[queue.getLast()])
                queue.removeLast();
            queue.addLast(i);
        }
        //遍历
        for (int i = n; i < num.length; i++) {
            res.add(num[queue.getFirst()]);
            while (!queue.isEmpty() && num[i] >= num[queue.getLast()])
                queue.removeLast();
            /**最大值过期*/
            if (!queue.isEmpty() && queue.getFirst() <= (i - n))
                queue.removeFirst();
            queue.addLast(i);
        }
        res.add(num[queue.getFirst()]);
        return res;
    }

    /**
     * No.59plus
     * 具有最大值的队列
     */
    public static class QueueWithMax {
        private Queue<InternalData> queue = new LinkedList<>();
        private Deque<InternalData> max = new LinkedList<>();
        private int index = 0;

        public void offer(int num) {
            InternalData internalData = new InternalData(num, index);
            queue.offer(internalData);
            while (!max.isEmpty() && num >= max.getLast().num) {
                max.removeFirst();
            }
            max.addLast(internalData);
            index++;
        }

        public int poll() {
            /**最大值过期*/
            if (max.getFirst().index == queue.peek().index)
                max.removeFirst();
            return queue.poll().num;
        }

        public int getMax() {
            return max.getFirst().num;
        }

        /**
         * 包装类
         */
        private static class InternalData {
            public int num;
            public int index;

            public InternalData(int num, int index) {
                this.num = num;
                this.index = index;
            }
        }
    }

    /**
     * No.60
     * n个骰子的点数
     *
     * @param n
     */
    public static void printProbability(int n) {
        if (n < 1)
            return;
        int min = n;
        int max = 6 * n;
        int total = 6;
        for (int i = 1; i < n; i++) {
            total *= 6;
        }
        /**每一种和所有的组合数*/
        int[] probability = new int[max - min + 1];
        for (int i = 1; i <= 6; i++)
            Probability(n, n, i, probability);
        for (int i = min; i <= max; i++) {
            /**打印每一种和的概率*/
            System.out.println("number " + i + " is " + (double) probability[i - min] / total);
        }
    }

    /**
     * 深度优先
     */
    public static void Probability(int original, int current, int sum, int[] probability) {
        if (current == 1) {
            probability[sum - original]++;
        } else {
            for (int i = 1; i <= 6; i++) {
                Probability(original, current - 1, i + sum, probability);
            }
        }
    }

    /**
     * No.61
     * 扑克牌顺子
     *
     * @param numbers
     * @return
     */
    public static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return false;
        int numOfZero = 0;
        int dis = 0;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                numOfZero++;
            } else {
                if (numbers[i + 1] == numbers[i])
                    return false;
                dis += numbers[i + 1] - numbers[i] - 1;
            }
        }
        if (dis <= numOfZero) {
            return true;
        }
        return false;
    }

    /**
     * No.62
     * 圆圈中最后剩下的数
     * 模拟
     *
     * @return
     */
    public static int getLastOne(int n, int m) {
        /*if(n<1||m<1)
            return -1;
        int last = 0;
        for(int i=2;i<=n;i++){
            last=(last+m)%i;
        }
        return last;*/
        if (m < 1 || n < 1)
            return -1;
        ListNode head = new ListNode(0);
        ListNode last = head;
        for (int i = 1; i < n; i++) {
            ListNode node = new ListNode(i);
            node.pre = last;
            last.next = node;
            last = node;
        }
        last.next = head;
        head.pre = last;
        int count = m;
        while (head.pre != head) {
            if (count == 1) {
                head.pre.next = head.next;
                head.next.pre = head.pre;
                head = head.next;
                count = m;
            } else {
                head = head.next;
                count--;
            }
        }
        return head.val;
    }

    /**
     * No.63
     * 股票交易
     *
     * @param price
     * @return
     */
    public static int stock(int[] price) {
        int max = 0;
        int lastMin = price[0];
        for (int i = 1; i < price.length; i++) {
            int cur = price[i] - lastMin;
            if (cur > max) {
                max = cur;
            }
            if (price[i] < lastMin) {
                lastMin = price[i];
            }
        }
        return max;
    }

    /**
     * No.64
     * 1+2+3+...+n
     * 逻辑运算判断终止条件
     *
     * @param n
     * @return
     */
    public static int addToN(int n) {
        int sum = n;
        boolean fin = n > 0 && (sum += addToN(n - 1)) > 0;
        return sum;
    }

    /**
     * No.65
     * 不使用四则运算实现加法
     * 位运算
     * 异或、位与、移位
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int sum, carry;
        while (b != 0) {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }

    /**
     * No.66
     * 不使用除法实现乘积数组
     *
     * @param A
     * @return
     */
    public static int[] multiply(int[] A) {
        int[] B = new int[A.length];
        B[0] = 1;
        for (int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int temp = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            temp *= A[i + 1];
            B[i] *= temp;
        }
        return B;
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

class ListNode {
    public int val;
    public ListNode pre;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

}

