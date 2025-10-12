/*
Question:
Given a positive integer num, return true if num is a perfect square or false otherwise.
A perfect square is an integer that is the square of an integer. 
In other words, it is the product of some integer with itself.
You must not use any built-in library function, such as sqrt.
*/
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;
        int left = 2, right = num / 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sq = (long) mid * mid;
            
            if (sq == num) return true;
            else if (sq < num) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}

/*
First Thought:
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;  
        
        for (int i = 0; i * i <= num; i++)
            if (i * i == num) return true;
        
        return false;
    }
}
*/