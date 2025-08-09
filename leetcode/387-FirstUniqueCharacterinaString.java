/*
Question:
Given a string s, find the first non-repeating character in it and return its index. 
If it does not exist, return -1.
*/

class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        
        for (int i = 0; i < s.length(); i++) freq[s.charAt(i) - 'a']++;
        
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1) return i;
        
        return -1;
    }
}

/*
First thought:
class Solution {
    public int firstUniqChar(String s) {
        for(int i=0;i<s.length();i++){
            boolean exist = false;
            for(int j=0;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j)&&exist == false&&i!=j) {exist = true; } 
            }
            if(exist == false) return i;
        }
        return -1;
    }
}
*/