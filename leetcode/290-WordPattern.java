/*
Question:
Given a pattern and a string s, find if s follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. 
Specifically:
- Each letter in pattern maps to exactly one unique word in s.
- Each unique word in s maps to exactly one letter in pattern.
- No two letters map to the same word, and no two words map to the same letter.
*/

public class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] word = s.split(" ");
        if (word.length != pattern.length()) return false;

        Map<Character, String> c = new HashMap<>();
        Map<String, Character> w = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String wh = word[i];

            if (c.containsKey(ch)) {
                if (!c.get(ch).equals(wh)) return false;
            } else {
                if (w.containsKey(wh)) return false;
                c.put(ch, wh);
                w.put(wh, ch);
            }
        }

        return true;
    }
}