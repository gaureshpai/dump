/*
Question:
You are given an array nums of non-negative integers and an integer k.
An array is called special if the bitwise OR of all of its elements is at least k.
Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
*/

class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int orSum = 0;

            for (int j = i; j < n; j++) {
                orSum |= nums[j];

                if (orSum >= k) {
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
            }
        }

        return (minLength == Integer.MAX_VALUE) ? -1 : minLength;
    }
}