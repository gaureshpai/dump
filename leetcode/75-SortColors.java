/*
Question:
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.
*/

class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for(int i = 0; i < nums.length; i++)
            if(nums[i] == 0) count0++;
            else if(nums[i] == 1) count1++;
            else if(nums[i] == 2) count2++;   
            
        int i = 0;
        
        while(count0 != 0 || count1 != 0 || count2 != 0){
            if(count0 != 0){
                nums[i] = 0;
                count0--;
            } else if(count1 != 0){
                nums[i] = 1;
                count1--;
            } else if(count2 != 0){
                nums[i] = 2;
                count2--;
            }
            i++;
        }
    }
}