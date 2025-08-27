/*
Question:
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
*/

class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, result = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) leftMax = height[left];
                else result += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right];
                else result += rightMax - height[right];
                right--;
            }
        }
        
        return result;
    }
}