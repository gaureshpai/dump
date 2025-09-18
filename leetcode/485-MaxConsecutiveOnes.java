/*
Question:
Given a binary array nums, return the maximum number of consecutive 1's in the array.
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, max = 0;

        for(int num: nums){
            if(num == 1) count++;
            else count  = 0;
            
            max = max > count ? max : count;
        }
        
        return max;
    }
}