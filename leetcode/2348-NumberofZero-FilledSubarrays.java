/*
Question:
Given an integer array nums, return the number of subarrays filled with 0.
A subarray is a contiguous non-empty sequence of elements within an array.
*/

class Solution {
    public static long zeroFilledSubarray(int[] nums) {
        long res = 0, count = 0;
        for (int num : nums)
            if (num == 0) {
                count++;
                res += count;
            } else count = 0;
        return res;
    }
}