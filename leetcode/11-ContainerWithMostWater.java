/*
Question:
You are given an integer array height of length n.
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.
*/

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, result = 0;

        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            result = Math.max(result, area);

            if (height[left] < height[right]) left++;
            else right--;
        }
        
        return result;
    }
}