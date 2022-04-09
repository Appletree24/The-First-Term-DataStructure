package com.appletree24.BinaryTree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree=new ArrBinaryTree(arr);
        arrBinaryTree.postOrder(0);
    }

}



class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }


    public void preOrder(){
        this.preOrder(0);
    }


    /**
     *  顺序存储二叉树的前序遍历
     *  完全二叉树的规律：
     *  一个节点的左子节点为2*n+1
     *  一个节点的右子节点为2*n+2
     *  一个节点的父节点为（n-1）/2；
     *  n均指在数组中的下标
     * @param index  数组下标 从0开始
     */
    public void preOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能前序遍历");
        }
        //符合前序遍历顺序，先输出自己，再按顺序输出左右
        System.out.println(arr[index]);
        //防止越界，加入判断
        if((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        if((index*2+2)<arr.length){
            preOrder(index*2+2);
        }
    }


    /**
     * 中序遍历顺序存储二叉树
     * @param index 数组下标
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能中序遍历");
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }


    /**
     * 后序遍历顺序存储二叉树
     * @param index 数组下标
     */
    public void postOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能后序遍历");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}


