/*
Question:
Given an array of strings words, return the first palindromic string in the array. 
If there is no such string, return an empty string "".
A string is palindromic if it reads the same forward and backward.
*/

class Solution {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            String reversed = new StringBuilder(word).reverse().toString();
            if (word.equals(reversed)) return word;
        }
        return "";
    }
}