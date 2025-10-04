/*
Question:
You are given two strings s and t such that every character occurs at most once in s and t is a permutation of s.
The permutation difference between s and t is defined as the sum of the absolute difference between the index of the occurrence of each character in s and the index of the occurrence of the same character in t.
Return the permutation difference between s and t.
*/

class Solution {
    public int findPermutationDifference(String s, String t) {
        int count = 0;

        for(int i = 0; i < s.length(); i++)
            for(int j = 0; j < t.length(); j++)
                if(s.charAt(i) == t.charAt(j))
                    count += Math.abs(i - j);

        return count;
    }
}