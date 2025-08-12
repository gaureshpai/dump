/*
Question:
You are given a positive integer array nums.
The element sum is the sum of all the elements in nums.
The digit sum is the sum of all the digits (not necessarily distinct) that appear in nums.
Return the absolute difference between the element sum and digit sum of nums.
Note that the absolute difference between two integers x and y is defined as |x - y|.
*/

class Solution {
    public int differenceOfSum(int[] nums) {
        int diff = 0;
        for(int i = 0; i < nums.length; i++){
            int sumOfDigits = 0;
            while(nums[i] != 0){
                sumOfDigits += nums[i]%10;
                nums[i] = nums[i]/10;
            }
            diff += nums[i] - sumOfDigits;
        }
        return diff;
    }
}