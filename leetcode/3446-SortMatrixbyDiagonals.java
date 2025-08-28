/*
Question:
You are given an n x n square matrix of integers grid. 
Return the matrix such that:
- The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
- The diagonals in the top-right triangle are sorted in non-decreasing order.
*/

public class Solution {
    public static int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        for (int d = - (n - 1); d <= n - 1; d++) {
            List<Integer> diag = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                int j = i - d;
                if (j >= 0 && j < n) diag.add(grid[i][j]);
            }
            
            if (d >= 0) diag.sort(Comparator.reverseOrder());
            else Collections.sort(diag);
            
            int index = 0;
            for (int i = 0; i < n; i++) {
                int j = i - d;
                if (j >= 0 && j < n) grid[i][j] = diag.get(index++);
            }
        }

        return grid;
    }
}