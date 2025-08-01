/*
Question:
Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:
- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
- Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.
*/

class Solution {
    public int happy(int n) {
        int num = 0;
        while (true) {
            if (n == 0) return num;
            num += (n % 10) * (n % 10);
            n = n / 10;
        }
    }
    public boolean isHappy(int n) {
        Set<Integer> x = new HashSet<>();
        while (true) {
            n = happy(n);
            if (n == 1) return true;
            if (!x.add(n)) return false;
        }
    }
}