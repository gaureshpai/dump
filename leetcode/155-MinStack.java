/*
Question:
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class:
- MinStack() initializes the stack object.
- void push(int val) pushes the element val onto the stack.
- void pop() removes the element on the top of the stack.
- int top() gets the top element of the stack.
- int getMin() retrieves the minimum element in the stack.
- You must implement a solution with O(1) time complexity for each function.
*/

import java.util.Stack;
class MinStack {
    private Stack<Integer> st;
    private Stack<Integer> ts;

    public MinStack() {
        st = new Stack<>();
        ts = new Stack<>();
    }

    public void push(int val) {
        if (ts.isEmpty() || val <= ts.peek()) ts.push(val);
        st.push(val);

    }

    public void pop() {
        int x = st.pop();
        if (x == ts.peek()) ts.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return ts.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */