/*
Question:
In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1. 
Each number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional time, making the list longer than usual.
As the town detective, your task is to find these two sneaky numbers. 
Return an array of size two containing the two numbers (in any order), so peace can return to Digitville.
*/

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] res = new int[2];
        int k = 0;
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++)
            for(int j = i + 1; j < nums.length; j++)
                if(nums[i] == nums[j]) res[k++] = nums[i];

        return res; 
    }
}
