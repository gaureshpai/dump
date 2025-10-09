/*
Question:
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
The integer division should truncate toward zero, which means losing its fractional part. 
For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
Return the quotient after dividing dividend by divisor.
Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1]. 
For this problem, if the quotient is strictly greater than 2^(31 - 1), then return 2^(31 - 1), and if the quotient is strictly less than -2^31, then return -2^31.
*/

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        boolean positive = (dividend < 0) == (divisor < 0);
        int divd = dividend > 0 ? -dividend : dividend;
        int divs = divisor > 0 ? -divisor : divisor;
        int res = 0;

        while (divd <= divs) {
            int temp = divs, multiple = 1;
            
            while (temp >= Integer.MIN_VALUE / 2 && divd <= temp + temp) {
                temp += temp;
                multiple += multiple;
            }
            divd -= temp;
            res += multiple;
        }

        return positive ? res : -res;
    }
}