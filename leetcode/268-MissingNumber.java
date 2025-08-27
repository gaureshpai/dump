/*
Question:
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
*/

class Solution {
    public int missingNumber(int[] nums) {
        int ans = (nums.length*(nums.length + 1))/2;
        for(int i = 0; i < nums.length; i++) ans = ans - nums[i];
        
        return ans;
    }
}

/*
First Thought:
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i!=nums[i]) return i;
        }
        return nums.length;
    }
}
*/