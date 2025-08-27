/*
Question:
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> sc = new HashMap<>();
        Map<Character, Integer> tc = new HashMap<>();

        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) sc.merge(s.charAt(i), 1, Integer::sum);
        for (int i = 0; i < t.length(); i++) tc.merge(t.charAt(i), 1, Integer::sum);
        if (sc.equals(tc)) return true;

        return false;
    }
}