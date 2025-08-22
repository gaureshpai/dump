/*
Question:
Given an array of integers nums, calculate the pivot index of this array.
The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. 
This also applies to the right edge of the array.
Return the leftmost pivot index. If no such index exists, return -1.
*/

class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length, total = 0;
        for (int i = 0; i < n; i++) total+=nums[i];
        int left = 0, right = total-nums[0];
        if (left == right) return 0;
        int i = 1;
        while(i < n){
            left += nums[i - 1];
            right = total - left - nums[i];
            if (left == right) return i;
            i++;
        }
        return -1;
    }
}

/*
First Thought:
class Solution {
    public int pivotIndex(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++) left[i] += nums[j];
            for(int j = i+1; j < nums.length; j++) right[i] += nums[j];
            if(left[i] == right[i]) return i;
        }
        return -1;
    }
}
*/