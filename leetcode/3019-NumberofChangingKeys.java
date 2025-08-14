/*
Question:
You are given a 0-indexed string s typed by a user. 
Changing a key is defined as using a key different from the last used key. 
For example, s = "ab" has a change of a key while s = "bBBb" does not have any.
Return the number of times the user had to change the key.
Note: Modifiers like shift or caps lock won't be counted in changing the key that is if a user typed the letter 'a' and then the letter 'A' then it will not be considered as a changing of key.
*/

class Solution {
    public int countKeyChanges(String s) {
        int count = 0;
        if(s.length() <= 1) return 0;
        String m = s.toLowerCase();
        for(int i = 0; i < m.length()-1; i++)
            if(m.charAt(i) != m.charAt(i+1)) count++;
        return count;
    }
}