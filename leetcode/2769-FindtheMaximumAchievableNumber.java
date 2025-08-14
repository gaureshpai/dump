/*
Question:
Given two integers, num and t. 
A number x is achievable if it can become equal to num after applying the following operation at most t times:
- Increase or decrease x by 1, and simultaneously increase or decrease num by 1.
Return the maximum possible value of x.
*/

class Solution {
    public int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }
}