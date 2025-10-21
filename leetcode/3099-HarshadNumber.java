/*
Question:
An integer divisible by the sum of its digits is said to be a Harshad number. 
You are given an integer x. 
Return the sum of the digits of x if x is a Harshad number, otherwise, return -1.
*/

class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0, og = x;

        while(x != 0){
            sum += x % 10;
            x = x / 10;
        }

        return og % sum == 0 ? sum : -1;
    }
}