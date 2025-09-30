/*
Question:
You are given an integer array nums. 
Transform nums by performing the following operations in the exact order specified:
- Replace each even number with 0.
- Replace each odd numbers with 1.
- Sort the modified array in non-decreasing order.
Return the resulting array after performing these operations.
*/

class Solution {
    public int[] transformArray(int[] nums) {
        int[] res = new int[nums.length];

        for(int i = 0; i < nums.length; i++)
            if(nums[i] % 2 == 0) res[i] = 0;
            else res[i] = 1;

        Arrays.sort(res);

        return res;
    }
}