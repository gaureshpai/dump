/*
Question:
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

import java.math.BigInteger;

class Solution {
    public static BigInteger convert(String s) {
        BigInteger num = BigInteger.ZERO;
        int n = s.length();

        for (int i = 0; i < n; i++) num = num.multiply(BigInteger.TEN).add(BigInteger.valueOf(s.charAt(i) - '0'));

        return num;
    }

    public String multiply(String num1, String num2) {
        BigInteger x = convert(num1);
        BigInteger y = convert(num2);
        BigInteger z = x.multiply(y);

        return z.toString();
    }
}

/*
First Thought:
class Solution {
    public String multiply(String num1, String num2) {
        long x = Long.parseLong(num1);
        long y = Long.parseLong()num2);
        long z = x*y;
        return String.valueOf(z);
    }
}
*/