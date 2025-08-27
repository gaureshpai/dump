/*
Question:
Given two binary strings a and b, return their sum as a binary string.
*/

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0, i = a.length() - 1, j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry != 0) {
            int bitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int bitB = (j >= 0) ? b.charAt(j) - '0' : 0;
            
            int sum = bitA + bitB + carry;
            res.append(sum % 2);
            carry = sum / 2;

            i--;
            j--;
        }
        
        return res.reverse().toString();
    }
}

/*
First Thought:
class Solution {
    public String addBinary(String a, String b) {
        if (a.equals("0")) return b;
        if (b.equals("0")) return a;
        int num1 = Integer.parseInt(a, 2);
        int num2 = Integer.parseInt(b, 2);
        int sum = num1 + num2;
        return Integer.toBinaryString(sum);
    }
}
*/