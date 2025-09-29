/*
Question:
Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. 
If it is impossible to form any triangle of a non-zero area, return 0.
*/

class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            int a = nums[i - 2], b = nums[i - 1], c = nums[i];

            if (a + b > c && b + c > a && a + c > b) return a + b + c;
        }
        
        return 0;
    }
}