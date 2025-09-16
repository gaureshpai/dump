/*
Question:
You are given an integer array nums and an integer k. 
You can perform the following operation any number of times:
- Select an index i and replace nums[i] with nums[i] - 1.
Return the minimum number of operations required to make the sum of the array divisible by k.
*/

class Solution {
    public int minOperations(int[] nums, int k) {
        int sum = 0;

        for(int i = 0; i < nums.length; i++) sum += nums[i];
        
        return sum % k;
    }
}