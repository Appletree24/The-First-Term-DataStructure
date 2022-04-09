package com.appletree24.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] j={45,98,1,6,2,8,4};
        selectSort(j);
        System.out.println(Arrays.toString(j));
    }


    public static void selectSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int minIndex=i;
            int min=arr[minIndex];
            for(int j=minIndex+1;j<arr.length;j++){
                if(min>arr[j]){
                    min=arr[j];
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
        }
    }
}
