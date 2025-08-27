/*
Question:
You are given two strings word1 and word2. 
Merge the strings by adding letters in alternating order, starting with word1. 
If a string is longer than the other, append the additional letters onto the end of the merged string.
Return the merged string.
*/

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int i = 0, j = 0, k = 0;
        String word3 = "";

        while(i != word1.length() && j != word2.length()){
            if(k%2 == 0) {
                word3 += word1.charAt(i);
                i++;
            } else {
                word3 += word2.charAt(j);
                j++;
            }
            k++;
        }
        
        while(i != word1.length()){
            word3 += word1.charAt(i);
            i++;
        }

        while(j != word2.length()){
            word3 += word2.charAt(j);
            j++;
        }

        return word3;
    }
}