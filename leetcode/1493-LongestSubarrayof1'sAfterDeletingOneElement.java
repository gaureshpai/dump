/*
Question:
Given a binary array nums, you should delete one element from it.
Return the size of the longest non-empty subarray containing only 1's in the resulting array. 
Return 0 if there is no such subarray.
*/

class Solution {
    public int longestSubarray(int[] nums) {
        int max = 0, count = 0, left = 0;
        boolean done = false;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 1) count++;
            else {
                if (!done) {
                    count++;
                    done = true;
                } else {
                    while (nums[left] == 1) {
                        left++;
                        count--;
                    }
                    left++;
                }
            }
            max = Math.max(max, count - 1);
        }
        return max;
    }
}