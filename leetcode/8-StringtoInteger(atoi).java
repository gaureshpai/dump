/*
Question:
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
The algorithm for myAtoi(string s) is as follows:
- Whitespace: Ignore any leading whitespace (" ").
- Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
- Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
- Rounding: If the integer is out of the 32-bit signed integer range [-2^31, 2^(31 - 1)], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 2^(31 - 1) should be rounded to 2^(31 - 1).
Return the integer as the final result.
*/

class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        
        while (i < n && s.charAt(i) == ' ') i++;

        int sign = 1;
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        long num = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            num = num * 10 + (s.charAt(i) - '0');
            if (sign * num <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
            if (sign * num >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            i++;
        }

        return (int) (sign * num);
    }
}