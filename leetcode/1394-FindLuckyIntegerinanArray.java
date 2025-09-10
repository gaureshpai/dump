/*
Question:
Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
Return the largest lucky integer in the array. 
If there is no lucky integer return -1.
*/

import java.util.Arrays;

class Solution {
    public int findLucky(int[] arr) {
        Arrays.sort(arr);

        int count = 1;

        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] == arr[i - 1]) count++;
            else {
                if (arr[i] == count) return arr[i];
                count = 1;
            }
        }
        
        if (arr[0] == count) return arr[0];

        return -1;
    }
}