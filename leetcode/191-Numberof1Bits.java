/*
Question:
Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).
*/

class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        
        return count;
    }
}

/*
First Thought:
class Solution {
    public int hammingWeight(int n) {
        String str = Integer.toBinaryString(n);
        int count=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='1') count++;
        }
        return count;
    }
}
*/