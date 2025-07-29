/*
Question:
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
*/

public int[] countBits(int n) {
    int[] result = new int[n + 1];
    for (int i = 1; i <= n; i++) result[i] = result[i >> 1] + (i & 1);
    return result;
}

/*
First Thought:
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }
    public int[] countBits(int n) {
        int[] result = new int[n+1];
        for(int i=0;i<=n;i++){
            result[i]+= hammingWeight(i);
        }  
        return result;
    }
}
*/