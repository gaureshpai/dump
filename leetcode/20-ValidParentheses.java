/*
Question:
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
- Open brackets must be closed by the same type of brackets.
- Open brackets must be closed in the correct order.
- Every close bracket has a corresponding open bracket of the same type.
*/

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        int x = s.length();
        int i = 0;
        
        if (s.length() < 1) return true;
        while (i != x) {
            char ch = s.charAt(i);
            if (!st.isEmpty() && (
                (st.peek() == '(' && ch == ')') ||
                (st.peek() == '{' && ch == '}') ||
                (st.peek() == '[' && ch == ']')
            )) st.pop();
            else st.push(ch);
            i++;
        }
        return st.isEmpty();
    }
}