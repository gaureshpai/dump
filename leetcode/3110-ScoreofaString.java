/*
You are given a string s. 
The score of a string is defined as the sum of the absolute difference between the ASCII values of adjacent characters.
Return the score of s.
*/

class Solution {
    public int scoreOfString(String s) {
        int count = 0;

        for(int i = 0; i < s.length() - 1; i++)
            count += Math.abs((s.charAt(i) - 'a') - (s.charAt(i + 1) - 'a'));

        return count;
    }
}