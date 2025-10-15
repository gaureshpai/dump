/*
Question:
Given an array nums of n integers, your task is to find the maximum value of k for which there exist two adjacent subarrays of length k each, such that both subarrays are strictly increasing. 
Specifically, check if there are two subarrays of length k starting at indices a and b (a < b), where:
- Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
- The subarrays must be adjacent, meaning b = a + k.
Return the maximum possible value of k.
A subarray is a contiguous non-empty sequence of elements within an array.
*/

class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] incLen = new int[n];
        incLen[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--)
            if (nums.get(i) < nums.get(i + 1)) incLen[i] = incLen[i + 1] + 1;
            else incLen[i] = 1;

        int left = 1, right = n / 2, ans = 0;

        while (left <= right) {
            int k = (left + right) / 2;
            boolean found = false;

            for (int i = 0; i <= n - 2 * k; i++)
                if (incLen[i] >= k && incLen[i + k] >= k) {
                    found = true;
                    break;
                }
            
            if (found) {
                ans = k;
                left = k + 1;
            } else right = k - 1;
        }
        
        return ans;
    }
}

/*
First Thought:
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        
        for (int i = 0; i <= n - 2 * k; i++) {
            boolean firstInc = true, secondInc = true;

            for (int j = 1; j < k; j++)
                if (nums.get(i + j - 1) >= nums.get(i + j)) {
                    firstInc = false;
                    break;
                }
            
            for (int j = 1; j < k; j++)
                if (nums.get(i + k + j - 1) >= nums.get(i + k + j)) {
                    secondInc = false;
                    break;
                }
            
            if (firstInc && secondInc) return true;
        }

        return false;
    }

    public int maxIncreasingSubarrays(List<Integer> nums) {
        for(int i = nums.size() - 1; i >=0; i--){
            if(hasIncreasingSubarrays(nums,i)) return i;
        }
        return 0;
    }
}
*/