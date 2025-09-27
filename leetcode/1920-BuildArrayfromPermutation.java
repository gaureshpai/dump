/*
Question:
Given a zero-based permutation nums (0-indexed), build an array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).
*/

class Solution {
    public int[] buildArray(int[] nums) {
        int[] res = new int[nums.length];

        for(int i = 0; i < nums.length; i++) res[i] = nums[nums[i]];

        return res;
    }
}