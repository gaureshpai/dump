/*
Question:
You are given a positive number n.
Return the smallest number x greater than or equal to n, such that the binary representation of x contains only set bits.
*/

class Solution {
    public int smallestNumber(int n) {
        String binaryString = Integer.toBinaryString(n);
        int len = binaryString.length();

        String str = "";
        
        for(int i = 0; i < len; i++)
            str += 1;

        return Integer.parseInt(str, 2);
    }
}