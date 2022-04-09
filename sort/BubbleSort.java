package com.appletree24.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] ={3,9,-1,10,20};
        boolean flag=false;
        for(int i=0;i<arr.length-1;i++){
            for(int j=i;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    flag=true;
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;
            }
            System.out.println(i);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }
}
