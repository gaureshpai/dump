/*
Question:
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. 
If you cannot achieve any profit, return 0.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int minimum = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (minimum > prices[i]) minimum = prices[i];
            if (res < (prices[i] - minimum)) res = prices[i] - minimum;
        }
        
        return res;
    }
}

/*
First Thought:
class Solution {
    public int maxProfit(int[] prices) {
        int[] res = new int[prices.length];
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                if(prices[i]<prices[j]&&(prices[j]-prices[i])>res[i]){
                    res[i] = prices[j]-prices[i];
                }
            }
        }
        int result = 0;
        for(int i=1;i<res.length;i++){
            if(res[i]>res[i-1]&&res[i]>result)
                result = res[i];
        }
        if(res[0]>result)result=res[0];
        return result;
    }
}
*/