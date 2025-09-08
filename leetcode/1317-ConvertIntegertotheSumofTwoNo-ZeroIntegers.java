/*
Question:
No-Zero integer is a positive integer that does not contain any 0 in its decimal representation.
Given an integer n, return a list of two integers [a, b] where:
- a and b are No-Zero integers.
- a + b = n
The test cases are generated so that there is at least one valid solution. 
If there are many valid solutions, you can return any of them.
*/

class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (!hasZero(a) && !hasZero(b)) return new int[]{a, b};
        }

        return new int[]{};
    }

    private boolean hasZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) return true;
            num /= 10;
        }
        
        return false;
    }
}