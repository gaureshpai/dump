/*
Question:
Given a signed 32-bit integer x, return x with its digits reversed. 
If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
*/

class Solution {
    public int reverse(int x) {
        int rev = 0;

        while (x != 0) {
            int digit = x % 10;

            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) return 0;

            rev = rev * 10 + digit;
            x = x / 10;
        }
        
        return rev;
    }
}

/*
First Thought:
class Solution {
    public int reverse(int x) {
        long rev = 0;

        while(x != 0){
            rev = rev * 10 + x%10;
            x = x/10;
        }

        if(rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
        
        return (int)rev;

    }
}
*/