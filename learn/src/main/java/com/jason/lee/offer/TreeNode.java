package com.jason.lee.offer;

class TreeNode {
    public TreeNode parent;
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
