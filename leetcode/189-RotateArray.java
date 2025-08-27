/*
Question:
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
*/

class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) res[(i + k) % nums.length] = nums[i];
        for (int i = 0; i < nums.length; i++) nums[i] = res[i];
    }
}