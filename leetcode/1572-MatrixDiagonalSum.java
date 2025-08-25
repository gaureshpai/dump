/*
Question:
Given a square matrix mat, return the sum of the matrix diagonals.
Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
*/

class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            res += mat[i][i];
            res += mat[i][n - i - 1];
        }

        if (n % 2 == 1)  res -= mat[n / 2][n / 2];
        
        return res;
    }
}