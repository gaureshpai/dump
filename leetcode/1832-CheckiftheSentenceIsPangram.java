/*
Question:
A pangram is a sentence where every letter of the English alphabet appears at least once.
Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
*/

class Solution {
    public boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26) return false;
        
        boolean[] ch = new boolean[26];
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = Character.toLowerCase(sentence.charAt(i));
            if (c >= 'a' && c <= 'z') ch[c - 'a'] = true;
        }
        
        for (int i = 0; i < 26; i++)
            if (!ch[i]) return false;
        
        return true;
    }
}
