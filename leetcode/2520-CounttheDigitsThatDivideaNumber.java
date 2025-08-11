/*
Question:
Given an integer num, return the number of digits in num that divide num.
An integer val divides nums if nums % val == 0.
*/

class Solution {
    public int countDigits(int num) {
        if(num <= 9) return 1;
        int fix = num, count = 0;
        while(num!=0){
            int x = num%10;
            num /= 10;
            if(fix%x == 0) count++;
        }
        return count;
    }
}