/*
Question:
You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
Evaluate the expression. Return an integer that represents the value of the expression.
Note that:
- The valid operators are '+', '-', '*', and '/'.
- Each operand may be an integer or another expression.
- The division between two integers always truncates toward zero.
- There will not be any division by zero.
- The input represents a valid arithmetic expression in a reverse polish notation.
- The answer and all the intermediate calculations can be represented in a 32-bit integer.
*/

class Solution {
    public int evalRPN(String[] tokens) {
        Stack <Integer> st = new Stack<>();
        int ele1, ele2;

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                ele1 = st.pop();
                ele2 = st.pop();
                st.push(ele2 + ele1);
            }
            else if (tokens[i].equals("-")) {
                ele1 = st.pop();
                ele2 = st.pop();
                st.push(ele2 - ele1);
            }
            else if (tokens[i].equals("*")) {
                ele1 = st.pop();
                ele2 = st.pop();
                st.push(ele2 * ele1);
            }
            else if (tokens[i].equals("/")) {
                ele1 = st.pop();
                ele2 = st.pop();
                st.push(ele2 / ele1);
            }
            else st.push(Integer.parseInt(tokens[i]));
        }
        
        return st.pop();
    }
}

/**
First Thought:
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int ele1 = st.pop();
                int ele2 = st.pop();
                st.push(ele2 + ele1);
            } else if (token.equals("-")) {
                int ele1 = st.pop();
                int ele2 = st.pop();
                st.push(ele2 - ele1);
            } else if (token.equals("*")) {
                int ele1 = st.pop();
                int ele2 = st.pop();
                st.push(ele2 * ele1);
            } else if (token.equals("/")) {
                int ele1 = st.pop();
                int ele2 = st.pop();
                st.push(ele2 / ele1);
            } else {
                st.push(Integer.parseInt(token));
            }
        }
        return st.peek();
    }
}
*/