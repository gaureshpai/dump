/*
Question:
Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing. 
Specifically, check if there are two subarrays starting at indices a and b (a < b), where:
- Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
- The subarrays must be adjacent, meaning b = a + k.
Return true if it is possible to find two such subarrays, and false otherwise.
*/

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
}