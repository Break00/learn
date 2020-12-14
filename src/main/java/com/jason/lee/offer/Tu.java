package com.jason.lee.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 图
 * 邻接矩阵
 */
public class Tu {

    private int numOfVertex;
    private int numOfEdge;
    private int[][] graph;
    private boolean[] isVisited;

    public Tu(int vertex) {
        this.numOfVertex = vertex;
        this.isVisited = new boolean[vertex];
        this.graph = new int[vertex][vertex];
    }

    /**
     * i 起点
     * j 终点
     * weight 权重
     *
     * @param i
     * @param j
     */
    public void insert(int i, int j, int weight) {
        this.graph[i - 1][j - 1] = weight;
        this.numOfEdge++;
    }

    /**
     * 深度优先遍历
     *
     * @param start
     */
    public void dfs(int start) {
        if (isVisited[start - 1])
            return;
        isVisited[start - 1] = true;
        System.out.print(start + " ");
        for (int i = 1; i <= numOfVertex; i++) {
            if (!isVisited[i - 1] && graph[start - 1][i - 1] != 0)
                dfs(i);
        }
    }

    /**
     * 广度优先遍历
     *
     * @param start
     */
    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start - 1] = true;
        while (!queue.isEmpty()) {
            int s = queue.poll();
            System.out.print(s + " ");
            for (int i = 1; i <= numOfVertex; i++) {
                if (!isVisited[i - 1] && graph[s - 1][i - 1] != 0) {
                    queue.offer(i);
                    isVisited[i - 1] = true;
                }
            }
        }
    }

    /**
     * 单源最短路径
     * 无权
     *
     * @param start
     */
    public void getDistance(int start) {
        int dis = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start - 1] = true;
        while (!queue.isEmpty()) {
            System.out.print("Dis=" + dis + ": ");
            int count = queue.size();
            while (count-- > 0) {
                int s = queue.poll();
                System.out.print(s + " ");
                for (int i = 1; i <= numOfVertex; i++) {
                    if (!isVisited[i - 1] && graph[s - 1][i - 1] != 0) {
                        queue.offer(i);
                        isVisited[i - 1] = true;
                    }
                }
            }
            System.out.println();
            dis++;
        }
    }

    /**
     * 单源最短路径
     * 有权（正）
     *
     * @param start
     */
    public long[] Dijkstra(int start) {

        //确定了最短路径的顶点集合
        ArrayList<Integer> lt = new ArrayList<>();
        long[] distance = new long[this.numOfVertex];

        //初始化
        for (int i = 0; i < distance.length; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[start - 1] = 0;

        int point = start;
        while (lt.size() < this.numOfVertex) {
            long min = Integer.MAX_VALUE;
            for (int i = 1; i <= distance.length; i++) {
                if (!isVisited[i - 1] && distance[i - 1] < min) {
                    min = distance[i - 1];
                    point = i;
                }
            }
            lt.add(point);
            isVisited[point - 1] = true;

            for (int i = 1; i <= distance.length; i++) {
                if (!isVisited[i - 1]) {
                    //松弛点
                    int index = lt.get(lt.size() - 1);
                    int gap = this.graph[index - 1][i - 1] == 0 ? Integer.MAX_VALUE : this.graph[index - 1][i - 1];
//                    if(gap<distance[i-1])
//                        distance[i-1]=gap;
                    if (gap + distance[index - 1] < distance[i - 1])
                        distance[i - 1] = gap + distance[index - 1];
                }
            }
        }
        return distance;
    }

    public void reset() {
        isVisited = new boolean[this.numOfVertex];
    }

}
