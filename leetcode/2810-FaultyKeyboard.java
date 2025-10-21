/*
Question:
Your laptop keyboard is faulty, and whenever you type a character 'i' on it, it reverses the string that you have written. 
Typing other characters works as expected.
You are given a 0-indexed string s, and you type each character of s using your faulty keyboard.
Return the final string that will be present on your laptop screen.
*/

class Solution {
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch != 'i') sb.append(ch);
            else {
                sb.reverse();
                continue;
            }
        }

        return sb.toString();
    }
}

/*
First thought:
class Solution {
    public String finalString(String s) {
        String res = "";

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != 'i')
                res += s.charAt(i);
            else{
                StringBuilder sb = new StringBuilder(res);
                res = sb.reverse().toString();
            }
        }
        return res;
    }
}
*/