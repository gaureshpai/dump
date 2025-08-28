/*
Question:
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
Each letter in magazine can only be used once in ransomNote.
*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for(char c : ransomNote.toCharArray()) freq1[c - 'a']++;
        for(char c : magazine.toCharArray()) freq2[c - 'a']++;
        
        for (int i = 0; i < 26; i++)
            if (freq1[i] > freq2[i]) return false;

        return true;
    }
}