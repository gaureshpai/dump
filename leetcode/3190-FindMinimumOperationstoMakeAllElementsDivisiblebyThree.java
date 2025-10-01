/*
Question:
You are given an integer array nums. 
In one operation, you can add or subtract 1 from any element of nums.
Return the minimum number of operations to make all elements of nums divisible by 3.
*/

class Solution {
    public int minimumOperations(int[] nums) {
        int num = 0;

        for(int i = 0; i < nums.length; i++)
            num += Math.min(nums[i] % 3, 3 - (nums[i] % 3));

        return num;
    }
}