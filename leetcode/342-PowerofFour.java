/*
Question:
Given an integer n, return true if it is a power of four. 
Otherwise, return false.
An integer n is a power of four, if there exists an integer x such that n == 4^x.
*/

class Solution {
    public boolean isPowerOfFour(int n) {
        if (n < 1) return false;

        while (n % 4 == 0) n /= 4;
        
        return n == 1;
    }
}