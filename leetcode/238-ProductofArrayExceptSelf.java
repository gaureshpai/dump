/*
Question:
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for (int i = 1; i < n; i++) result[i] = result[i - 1] * nums[i - 1];

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }
}

/* First thought: Higher time complexity
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] x = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int product = 1;
            for(int j=0;j<nums.length;j++){
                if(i!=j) product = product*nums[j];
            }
            x[i] = product;
        }
        return x;
    }
}*/