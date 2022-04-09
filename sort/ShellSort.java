package com.appletree24.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr=new int[800000];
        for(int i=0;i<800000;i++){
            arr[i]=(int)(Math.random()*80000000);
        }
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str=simpleDateFormat.format(date1);
        System.out.println("排序前："+date1Str);
        ShellSort2(arr);
        Date date2=new Date();
        String date2Str=simpleDateFormat.format(date2);
        System.out.println("排序前："+date2Str);
    }


    public static void ShellSort(int[] arr){
        int temp=0;
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i< arr.length;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
        }
    }



    public static void ShellSort2(int[] arr){
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int j=i;
                int temp=arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0&&temp<arr[j-gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=temp;
                }
            }
        }
    }
}
