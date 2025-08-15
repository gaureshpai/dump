/*
Question:
You are given a string num representing a large integer. 
An integer is good if it meets the following conditions:
- It is a substring of num with length 3.
- It consists of only one unique digit.
Return the maximum good integer as a string or an empty string "" if no such integer exists.

Note:
- A substring is a contiguous sequence of characters within a string.
- There may be leading zeroes in num or a good integer.
*/

class Solution {
    public String largestGoodInteger(String num) {
     String[] res = {"999", "888", "777", "666", "555", "444", "333", "222", "111", "000"};
     for(String i : res)
        if(num.contains(i)) return i;
     return "";
    }
}

/*
First Thought:
class Solution {
    public String largestGoodInteger(String num) {
        String res = "", fin = "";
        for(int i = 0; i < num.length() - 2; i++){
            if(num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)){
                res = "" + num.charAt(i) + num.charAt(i) + num.charAt(i);
                if(fin.equals("") || Integer.parseInt(res) >= Integer.parseInt(fin)){
                    fin = res;
                }
            }
        }
        return fin;
    }
}
*/