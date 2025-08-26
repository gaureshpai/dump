/*
Question:
Given an integer n, return true if it is a power of two. 
Otherwise, return false.
An integer n is a power of two, if there exists an integer x such that n == 2^x.
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}

/*
First Thought:
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }
}
*/