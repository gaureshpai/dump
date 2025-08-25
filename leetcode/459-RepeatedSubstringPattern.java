/*
Question:
Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2; i++)
            if (s.length() % i == 0) {
                String str = s.substring(0, i);
                boolean yes = true;
                
                for (int j = i; j < s.length(); j += i)
                    if (!s.substring(j, j + i).equals(str)) {
                        yes = false;
                        break;
                    }
                
                if (yes) return true;
            }
        return false;
    }
}