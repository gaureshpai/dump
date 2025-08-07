/*
Question:
Given an m x n integer matrix 'matrix', if an element is 0, set its entire row and column to 0's.
You must do it in place.
*/

class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] rowZ = new boolean[row];
        boolean[] colZ = new boolean[col];

        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                if(matrix[i][j] == 0) {
                    rowZ[i] = true;
                    colZ[j] = true;
                }

        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                if(rowZ[i] || colZ[j]) matrix[i][j] = 0;
    }
}