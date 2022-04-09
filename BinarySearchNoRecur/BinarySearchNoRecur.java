package com.appletree24.BinarySearchNoRecur;

/**
 * @author Appletree24
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr={1,3,8,10,11,67,138};
        int index=binarySearch(arr,100);
        System.out.println(index);
    }


    /**
     *
     * @param arr 带查找数组
     * @param target  目标数
     * @return 返回对应下标
     */
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        System.out.println("数组中不存在此数字");
        return -1;
    }
}
