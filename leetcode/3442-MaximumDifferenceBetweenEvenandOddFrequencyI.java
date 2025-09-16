/*
Question:
You are given a string s consisting of lowercase English letters.
Your task is to find the maximum difference diff = freq(a1) - freq(a2) between the frequency of characters a1 and a2 in the string such that:
- a1 has an odd frequency in the string.
- a2 has an even frequency in the string.
Return this maximum difference.
*/

class Solution {
    public int maxDifference(String s) {
        int[] freq = new int[26];
        int maxOdd = Integer.MAX_VALUE, maxEven = 0;

        for(int i = 0; i < s.length(); i++) freq[s.charAt(i) - 'a']++;
        
        Arrays.sort(freq);
        
        for(int i =  25; i >= 0; i--){
            if(freq[i] == 0) break;
            if(freq[i] % 2 == 0) maxOdd = Math.min(freq[i], maxOdd);
            else if(freq[i] % 2 == 1) maxEven = Math.max(freq[i], maxEven);
        }
        
        return maxEven - maxOdd;
    }
}