/*
Question:
You are climbing a staircase.
It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?
*/



/*
First Thought:
class Solution {
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }
}
*/