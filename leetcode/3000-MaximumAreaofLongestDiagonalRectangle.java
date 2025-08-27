/*
Question:
You are given a 2D 0-indexed integer array dimensions.
For all indices i, 0 <= i < dimensions.length, dimensions[i][0] represents the length and dimensions[i][1] represents the width of the rectangle i.
Return the area of the rectangle having the longest diagonal. 
If there are multiple rectangles with the longest diagonal, return the area of the rectangle having the maximum area.
*/

class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int max = 0, maxArea = 0;

        for (int[] dim : dimensions) {
            int dig = dim[0] * dim[0] + dim[1] * dim[1];
            int area = dim[0] * dim[1];
            
            if (dig > max) {
                max = dig;
                maxArea = area;
            } else if (dig == max) maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}

/*
First Thought: (Wrong for same diagonal different area)
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        double maxDig = Double.MIN_VALUE;
        int maxArea = 0;
        for (int i = 0; i < dimensions.length; i++) {
            double dig = Math.sqrt(dimensions[i][0] * dimensions[i][0] + dimensions[i][1] * dimensions[i][1]);
            int area = dimensions[i][0] * dimensions[i][1];
            if (dig > maxDig) {
                maxDig = dig;
                maxArea = area;
            }
        }
        return maxArea;
    }
}
*/