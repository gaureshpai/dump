/*
Question:
The Tribonacci sequence Tn is defined as follows: 
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
Given n, return the value of Tn.
*/

class Solution {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int zero = 0, one = 1, two = 1;
        for (int i = 3; i <= n; i++) {
            int next = zero + one + two;
            zero = one;
            one = two;
            two = next;
        }
        return two;
    }
}