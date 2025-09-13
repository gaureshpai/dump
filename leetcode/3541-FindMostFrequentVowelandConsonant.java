/*
Question:
You are given a string s consisting of lowercase English letters ('a' to 'z').
Your task is to:
- Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
- Find the consonant (all other letters excluding vowels) with the maximum frequency.
Return the sum of the two frequencies.
Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. 
If there are no vowels or no consonants in the string, consider their frequency as 0.
The frequency of a letter x is the number of times it occurs in the string.
*/

class Solution {
    public int maxFreqSum(String s) {
        int maxVowel = 0, maxCons = 0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int count = 1;

            for(int j = i + 1; j < s.length(); j++) 
                if(c == s.charAt(j)) count++;

            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') maxVowel = Math.max(count, maxVowel);
            else maxCons = Math.max(count, maxCons);
        }

        return maxVowel + maxCons;
    }
}