/*
Question:
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
*/

class Solution {
    public int maxVowels(String s, int k) {
        int max = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') count++;
            if (i >= k) {
                char prev = Character.toLowerCase(s.charAt(i - k));
                if (prev == 'a' || prev == 'e' || prev == 'i' || prev == 'o' || prev == 'u') count--;
            }
            if (i >= k - 1) max = Math.max(max, count);
        }
        return max;
    }
}

/*
First Thought:
class Solution {
    public int maxVowels(String s, int k) {
        int max = Integer.MIN_VALUE,count = 0;
        for(int i =0; i <= s.length() - k; i++){
            count = 0;
            for(int j = i; j < i + k; j++){
                char c = Character.toLowerCase(s.charAt(j));
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') count++;
            }
            if(count > max) max = count;
        }
        return max;
    }
}
*/