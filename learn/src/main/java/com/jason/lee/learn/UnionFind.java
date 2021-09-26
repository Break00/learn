package com.jason.lee.learn;

/**
 * 并查集---判断连通关系
 * 数组存储+树结构
 */
public class UnionFind {

    private int[] array;

    public UnionFind(int len) {
        this.array = new int[len];
        for (int i = 0; i < len; i++)
            array[i] = -1;            //初始化根节点-1
    }

    /**
     * 合并---建立关系
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int i = findRoot(p);
        int j = findRoot(q);
        if (i == j)
            return;
        else {
            if (array[i] < array[j]) {
                array[i] += array[j];
                array[j] = i;
            } else {
                array[j] += array[i];
                array[i] = j;
            }
        }
    }

    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q) ? true : false;
    }

    /**
     * 寻找根节点
     *
     * @param p
     * @return
     */
    private int findRoot(int p) {
        while (array[p] > 0)
            p = array[p];
        return p;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(7);
        uf.union(1, 2);
        uf.union(1, 3);
        uf.union(2, 6);
        uf.union(4, 5);
        uf.union(4, 6);
        System.out.println(uf.isConnected(2, 5));
    }

}
