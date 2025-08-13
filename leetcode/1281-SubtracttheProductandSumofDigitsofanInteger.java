/*
Question:
Given an integer number n, return the difference between the product of its digits and the sum of its digits.
*/

class Solution {
    public int subtractProductAndSum(int n) {
        int sum = 0, prod = 1, x;
        boolean isZero = false;
        while(n != 0){
            x = n%10;
            n /= 10;
            sum += x;
            prod *=x;
            if(x == 0) isZero = true;
        }
        if(isZero) return 0 - sum;
        return prod - sum;
    }
}