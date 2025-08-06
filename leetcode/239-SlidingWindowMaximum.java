/*
Question:
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position.
Return the max sliding window.
*/
import java.util.*;

class MaxStack {
    private Stack<Integer> st;
    private Stack<Integer> maxSt;

    public MaxStack() {
        st = new Stack<>();
        maxSt = new Stack<>();
    }

    public void push(int val) {
        st.push(val);
        if (maxSt.isEmpty() || val >= maxSt.peek()) maxSt.push(val);
    }

    public int pop() {
        int val = st.pop();
        if (val == maxSt.peek()) maxSt.pop();
        return val;
    }

    public int top() {
        return st.peek();
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int getMax() {
        if (maxSt.isEmpty()) return Integer.MIN_VALUE; 
        return maxSt.peek();
    }
}

class MaxQueue {
    private MaxStack inStack;
    private MaxStack outStack;

    public MaxQueue() {
        inStack = new MaxStack();
        outStack = new MaxStack();
    }

    public void push(int val) {
        inStack.push(val);
    }

    public void pop() {
        if (outStack.isEmpty())
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
        outStack.pop();
    }

    public int getMax() {
        if (inStack.isEmpty() && outStack.isEmpty()) return Integer.MIN_VALUE;
        else if (inStack.isEmpty()) return outStack.getMax();
        else if (outStack.isEmpty()) return inStack.getMax();
        else return Math.max(inStack.getMax(), outStack.getMax());
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return new int[0];
        int n = nums.length;
        int[] res = new int[n - k + 1];
        MaxQueue queue = new MaxQueue();

        for (int i = 0; i < k; i++) queue.push(nums[i]);
        res[0] = queue.getMax();

        for (int i = k; i < n; i++) {
            queue.pop();
            queue.push(nums[i]);
            res[i - k + 1] = queue.getMax();
        }
        return res;
    }
}

/*
First Thought:
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int left=0,right=k-1;
        List<Integer> list = new ArrayList<>();
        
        while(right!=nums.length){
            int max = Integer.MIN_VALUE;
            for(int i=left;i<=right;i++){
                if(max<nums[i]) max = nums[i];
            }
            list.add(max);
            left++;
            right++;
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }
}
*/

/*
Second Thought:
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        int left = 0, right = k - 1;
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        while (right < n) {
            if (maxIdx >= left && maxIdx <= right) {
                if (nums[right] > max) {
                    max = nums[right];
                    maxIdx = right;
                }
            } else {
                max = nums[left];
                maxIdx = left;
                for (int i = left + 1; i <= right; i++) {
                    if (nums[i] > max) {
                        max = nums[i];
                        maxIdx = i;
                    }
                }
            }
            result.add(max);
            left++;
            right++;
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) res[i] = result.get(i);
        return res;
    }
}

*/