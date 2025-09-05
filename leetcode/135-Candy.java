/*
Question:
There are n children standing in a line. 
Each child is assigned a rating value given in the integer array ratings.
You are giving candies to these children subjected to the following requirements:
- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.
*/

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length, total = 0;
        
        if (n == 0) return 0;

        int[] candies = new int[n];
        for (int i = 0; i < n; i++) candies[i] = 1;

        for (int i = 1; i < n; i++)
            if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;

        for (int i = n - 2; i >= 0; i--)
            if (ratings[i] > ratings[i + 1]) candies[i] = Math.max(candies[i], candies[i + 1] + 1);

        for (int candy : candies) total += candy;

        return total;
    }
}