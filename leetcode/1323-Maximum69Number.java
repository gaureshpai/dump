/*
Question:
You are given a positive integer num consisting only of digits 6 and 9.
Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
*/

class Solution {
    public int maximum69Number(int num) {
        String s = Integer.toString(num);
        int[] digits = new int[s.length()];
        boolean done = false;
        num = 0;
        for (int i = 0; i < s.length(); i++) {
            digits[i] = Character.getNumericValue(s.charAt(i));
            if(digits[i] == 6 && done == false) {
                digits[i] = 9;
                done = true;
            }
            num = num * 10 + digits[i];
        }
        return num;
    }
}

/*
First Thought:
class Solution {
    public int maximum69Number (int num) {
        String s = Integer.toString(num);
        int[] digits = new int[s.length()];
        boolean done = false;

        for (int i = 0; i < s.length(); i++) {
            digits[i] = Character.getNumericValue(s.charAt(i));
        }

        for (int i = 0; i < digits.length; i++) {
            if(digits[i] == 6 && done == false) {
                digits[i] = 9;
                done = true;
            }
        }
        num = 0;
        for (int i = 0; i < digits.length; i++) {
            num = num*10 + digits[i];
        }
        return num;
    }
}
*/