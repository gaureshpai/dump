/*
Question:
You are given a 0-indexed integer array nums and two integers key and k. 
A k-distant index is an index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
Return a list of all k-distant indices sorted in increasing order
*/

class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        boolean[] t = new boolean[n];
        List<Integer> res = new ArrayList<>();
        
        for (int j = 0; j < n; j++)
            if (nums[j] == key) {
                int left = Math.max(0, j - k);
                int right = Math.min(n - 1, j + k);
                for (int i = left; i <= right; i++) t[i] = true;
            }
        
        for (int i = 0; i < n; i++)
            if (t[i]) res.add(i);

        return res;
    }
}