package com.jason.lee.learn;

/**
 * 最大堆
 * 数组实现
 */
public class BinaryHeap {
    private int index=-1;
    private int capacity;
    private int[] array;

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }

    //（插入）上滤
    public void insert(int num){
        ++index;
        if(index>capacity-1)
            throw new ArrayIndexOutOfBoundsException();
        int hole;
        if(index==0){
            array[index]=num;
            return;
        }
        for(hole=index;num>array[(hole-1)/2]&&hole>0;hole=(hole-1)/2){
            array[hole]=array[(hole-1)/2];
        }
        array[hole]=num;
    }
    //（删除）下滤
    public void remove(){
        if(index<0)
            throw new ArrayIndexOutOfBoundsException();
        int last = array[index];
        index--;
        int hole,child;
        for(hole=0;2*hole+1<index;hole=child){
            child = 2*hole+1;
            if(child<index-1&&array[child]<array[child+1])
                child++;
            if(array[child]>last)
                array[hole]=array[child];
            else
                break;
        }
        array[hole]=last;
    }
    public int size(){
        return index+1;
    }
    public void printer(){
        for(int i=0;i<size();i++)
            System.out.println(array[i]);
    }

    public static void main(String[] args) {
        BinaryHeap maxHeap = new BinaryHeap(5);
        maxHeap.insert(3);
        maxHeap.insert(4);
        maxHeap.insert(5);
        maxHeap.insert(8);
        maxHeap.insert(11);
        maxHeap.remove();
        maxHeap.remove();
        maxHeap.printer();
    }
}
