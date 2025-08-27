/*
Question:
Given a sorted array of distinct integers and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity.
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int i = 1;

        if (target < nums[0]) return 0;
        else if (target == nums[0]) return 0;

        while (i < nums.length) {
            if (nums[i] == target) return i;
            else if (nums[i] > target && nums[i - 1] < target) return i;
            i++;
        }
        
        return nums.length;
    }
}