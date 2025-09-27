/*
Question:
You have n boxes. 
You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
In one operation, you can move one ball from a box to an adjacent box. 
Box i is adjacent to box j if abs(i - j) == 1. 
Note that after doing so, there may be more than one ball in some boxes.
Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
Each answer[i] is calculated considering the initial state of the boxes.
*/

class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (boxes.charAt(j) == '1') res[i] += Math.abs(j - i);

        return res;
    }
}