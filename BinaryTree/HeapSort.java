package com.appletree24.BinaryTree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr={4,6,8,5,9,-1,89,20,1,35,60,2};
        heapSort(arr);
    }

    public static void heapSort(int[] arr){
        int temp=0;
//        System.out.println("堆排序：");
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
        //i为有几个叶子节点
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        for(int j=arr.length-1;j>0;j--){
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }
        System.out.println("数组"+ Arrays.toString(arr));
    }

    /**
     * 将以i对应的非叶子节点的树调整成大底堆
     * @param arr 待调整数组
     * @param i  非叶子节点在数组中的索引
     * @param length  调整元素的个数（数字不固定，在逐渐减少）
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];
        //开始调整
        for(int k=i*2+1;k<length;k=k*2+1){
            //左子节点的值小于右子节点的值
            if(k+1<length&&arr[k]<arr[k+1]){
                k++;//k指向右子节点
            }
            //arr[k]已经指向了左子节点和右子节点的较大的那一个
            if(arr[k]>temp){
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        arr[i]=temp;
    }

}
