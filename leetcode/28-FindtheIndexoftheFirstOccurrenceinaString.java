/*
Question:
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0; i < haystack.length(); i++){
            int count = 0, k = i;

            if(k + needle.length() <= haystack.length())
                for(int j = 0; j < needle.length(); j++){
                    if(haystack.charAt(k) == needle.charAt(j)) count++;
                    else break;
                    
                    k++;
                }

            if(count == needle.length()) return i;
        }

        return -1;
    }
}