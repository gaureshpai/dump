/*
Question:
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
*/



/*
First Thought:
class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] pro = new int[heights.length];
        Arrays.fill(pro,1);
        int res=0;
        for(int i=0;i<heights.length;i++){
            int width = 1;
            for(int j=i+1;j<heights.length;j++){
                if(heights[i]<=heights[j]){
                    width+=1;
                }else{
                    break;
                }
            }
            for(int k=i-1;k>=0;k--){
                if(heights[i]<=heights[k]){
                    width+=1;
                }else{
                    break;
                }
            }
            pro[i] = heights[i]*width;
        }
        for(int i=0;i<pro.length;i++){
            res = Math.max(res, pro[i]);
        }
        return res;
    }
}
*/