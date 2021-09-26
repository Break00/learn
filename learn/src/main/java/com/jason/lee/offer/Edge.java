package com.jason.lee.offer;

/**
 * 边
 */
public class Edge {
    /**
     * 边的尾节点编号
     */
    public int index;
    /**
     * 边的权重
     */
    public int weight;
    /**
     * 其他边
     */
    public Edge edge;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
