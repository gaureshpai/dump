/*
Question:
A word is considered valid if:
- It contains a minimum of 3 characters.
- It contains only digits (0-9), and English letters (uppercase and lowercase).
- It includes at least one vowel.
- It includes at least one consonant.
You are given a string word.
Return true if word is valid, otherwise, return false.

Notes:
- 'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
- A consonant is an English letter that is not a vowel.
*/

class Solution {
    public boolean isValid(String word) {
        if (word.length() < 3) return false;
        boolean hasVowel = false, hasConsonant = false;
        for (char c : word.toCharArray()) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) return false;
            char lower = Character.toLowerCase(c);
            if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') hasVowel = true;
            else if (lower >= 'a' && lower <= 'z') hasConsonant = true;
        }
        return hasVowel && hasConsonant;
    }
}