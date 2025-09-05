/*
Question:
You are given a 2D matrix grid of size m x n. 
You need to check if each cell grid[i][j] is:
- Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
- Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
Return true if all the cells satisfy these conditions, otherwise, return false.
*/

class Solution {
    public boolean satisfiesConditions(int[][] grid) {
        for(int i = 0; i < grid[0].length - 1; i++)
            if(grid[0][i] == grid[0][i + 1]) return false;

        for(int i = 0; i < grid.length - 1; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] != grid[i + 1][j]) return false;

        return true;
    }
}