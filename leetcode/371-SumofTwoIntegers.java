/*
Question:
Given two integers a and b, return the sum of the two integers without using the operators + and -.
*/

class Solution {
    public int getSum(int a, int b) {
        while(b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        
        return a;
    }
}

/*
First Thought:
class Solution {
    public int getSum(int a, int b) {
        return a+b;
    }
}
*/