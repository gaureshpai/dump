/*
Question:
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. 
You may assume that the majority element always exists in the array.
*/

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int count = 1, majorityCount = nums.length / 2;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) count++;
            else  count = 1;
            
            if (count > majorityCount) return nums[i];
        }
        
        return nums[0];
    }
}