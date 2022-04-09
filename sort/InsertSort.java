package com.appletree24.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr={101,34,119,1};
        InsertSort(arr);
        System.out.printf(Arrays.toString(arr));
    }

    public static void InsertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertVal=arr[i];
            int insertIndex=i-1;
            while(insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertVal;
        }
    }
}
