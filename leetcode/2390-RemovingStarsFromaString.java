/*
Question:
You are given a string s, which contains stars *.
In one operation, you can:
- Choose a star in s.
- Remove the closest non-star character to its left, as well as remove the star itself.
Return the string after all stars have been removed.

Note:
- The input will be generated such that the operation is always possible.
- It can be shown that the resulting string will always be unique.
*/

class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray())
            if(c == '*')
                if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
            else sb.append(c);
            
        return sb.toString();
    }
}