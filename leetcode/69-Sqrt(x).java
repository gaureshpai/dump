/*
Question:
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. 
The returned integer should be non-negative as well.
You must not use any built-in exponent function or operator.
- For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
*/

class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;

        int low = 1, high = x, ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (mid <= x / mid) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        
        return ans;
    }
}

/*
First Thought:
class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;

        for (long i = 1; i <= x; i++)
            if (i > x / i) return (int) (i - 1);
            
        return -1;
    }
}
*/