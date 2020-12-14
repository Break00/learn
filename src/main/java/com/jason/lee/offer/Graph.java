package com.jason.lee.offer;

import java.util.Scanner;

/**
 * 图
 * 邻接表
 */
public class Graph {
    /**
     * 顶点数
     */
    public int vertexs;
    /**
     * 边数
     */
    public int edges;
    public Vertex[] verArray;

    public Graph() throws Exception {
        Scanner in = new Scanner(System.in);
        this.vertexs = in.nextInt();
        this.edges = in.nextInt();
        this.verArray = new Vertex[vertexs];

        /**初始化顶点*/
        for (int i = 0; i < vertexs; i++) {
            Vertex vertex = new Vertex();
            vertex.index = in.nextInt();
            vertex.edge = null;
            verArray[i] = vertex;
        }

        /**初始化边*/
        for (int i = 0; i < edges; i++) {
            int head = in.nextInt();
            int weight = in.nextInt();
            int tail = in.nextInt();
            Vertex vertex = getVertex(head);
            if (vertex == null)
                throw new Exception("初始化错误");
            Edge edge = new Edge();
            edge.index = tail;
            edge.weight = weight;
            /**从头插入*/
            edge.edge = vertex.edge;
            vertex.edge = edge;
        }

    }

    public Vertex getVertex(int index) {
        for (Vertex v : verArray) {
            if (v.index == index)
                return v;
        }
        return null;
    }

    public void printGraph() {
        for (Vertex v : this.verArray) {
            System.out.print(v.index + ": ");
            Edge edge = v.edge;
            while (edge != null) {
                System.out.print(edge.index + " ");
                edge = edge.edge;
            }
            System.out.println();
        }
    }

}
