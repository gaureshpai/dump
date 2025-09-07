/*
Question:
Given an integer n, return any array containing n unique integers such that they add up to 0.
*/

class Solution {
    public int[] sumZero(int n) {
        int x = -1 * (n * (n - 1))/2;
        int[] res = new int[n];
        res[0] = x;
        for(int i = 1; i < n; i++)
            res[i] = i;
        
        return res;
    }
}