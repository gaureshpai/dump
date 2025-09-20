/*
Question:
Given an array nums of integers, return how many of them contain an even number of digits.
*/

class Solution {
    public int findNumbers(int[] nums) {
        int count  = 0;

        for(int num: nums){
            String str = ""+ num;
            
            if(str.length() % 2 == 0) count++;
        }
            
        return count;
    }
}