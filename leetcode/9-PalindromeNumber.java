/*
Question:
Given an integer x, return true if x is a palindrome, and false otherwise.
*/

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int num = x, rev = 0;

        while (x != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        
        return num == rev;
    }
}

/*
First Thought:
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        String y = String.valueOf(x);
        String m = "";
        for(int i=y.length()-1;i>=0;i--){
            m+=y.charAt(i);
        }
        for(int i=y.length()-1;i>=0;i--){
            if(y.charAt(i)!=m.charAt(i)) return false;
        }
        return true;
    }
}
*/