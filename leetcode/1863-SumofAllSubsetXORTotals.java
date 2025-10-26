/*
Question:
The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums. 
Note: Subsets with the same elements should be counted multiple times.
An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
*/

class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length, sum = 0, N = 1 << n;

        for (int j = 0; j < N; j++) {
            int currentXOR = 0;

            for (int i = 0; i < n; i++)
                if ((j >> i & 1) == 1) currentXOR ^= nums[i];

            sum += currentXOR;
        }
        
        return sum;
    }
}