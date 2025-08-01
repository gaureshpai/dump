/*
Question:
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.
*/

class Solution {
    public boolean isPalindrome(String s) {
        int len = s.length();
        StringBuilder str = new StringBuilder();
        StringBuilder rev = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') str.append(s.charAt(i));
            else if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z') str.append(Character.toLowerCase(s.charAt(i)));
            else if ('0' <= s.charAt(i) && s.charAt(i) <= '9') str.append(s.charAt(i));

        }
        for (int i = str.length() - 1; i >= 0; i--) rev.append(str.charAt(i));
        if (!str.toString().equals(rev.toString())) return false;
        return true;
    }
}