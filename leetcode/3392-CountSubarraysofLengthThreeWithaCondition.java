/*
Question:
Given an integer array nums, return the number of subarrays of length 3 such that the sum of the first and third numbers equals exactly half of the second number.
*/

class Solution {
    public int countSubarrays(int[] nums) {
        int count  = 0;
        
        for(int i = 0; i < nums.length - 2; i++)
            if((double)(nums[i] + nums[i + 2]) == (double)(nums[i + 1] / 2.0)) count++;
        
        return count;
    }
}