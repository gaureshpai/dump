/*
Question:
A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
You are given an array of strings sentences, where each sentences[i] represents a single sentence.
Return the maximum number of words that appear in a single sentence.
*/

class Solution {
    public int mostWordsFound(String[] sentences) {
        int old = 0, new1 = 0;
        
        for (int i = 0; i < sentences.length; i++) {
            new1 = 0;
            for (int j = 0; j < sentences[i].length(); j++)
                if (sentences[i].charAt(j) == ' ') new1 += 1;
            if (old < new1) old = new1;
        }

        return old + 1;
    }
}