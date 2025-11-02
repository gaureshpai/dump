/*
Question:
You are given two integers m and n representing a 0-indexed m x n grid. 
You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. 
A cell is guarded if there is at least one guard that can see it.
Return the number of unoccupied cells that are not guarded.
*/

class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] arr = new int[m][n];
        int count = 0;

        for (int i = 0; i < guards.length; i++) arr[guards[i][0]][guards[i][1]] = 1;

        for (int i = 0; i < walls.length; i++) arr[walls[i][0]][walls[i][1]] = 2;

        for (int i = 0; i < m; i++) {
            boolean canGuard = false;

            for (int j = 0; j < n; j++)
                if (arr[i][j] == 1) canGuard = true;
                else if (arr[i][j] == 2) canGuard = false;
                else if (canGuard) arr[i][j] = 3;
            
            canGuard = false;

            for (int j = n - 1; j >= 0; j--)
                if (arr[i][j] == 1) canGuard = true;
                else if (arr[i][j] == 2) canGuard = false;
                else if (canGuard) arr[i][j] = 3;
        }

        for (int j = 0; j < n; j++) {
            boolean canGuard = false;

            for (int i = 0; i < m; i++)
                if (arr[i][j] == 1) canGuard = true;
                else if (arr[i][j] == 2) canGuard = false;
                else if (canGuard) arr[i][j] = 3;
            
            canGuard = false;

            for (int i = m - 1; i >= 0; i--)
                if (arr[i][j] == 1) canGuard = true;
                else if (arr[i][j] == 2) canGuard = false;
                else if (canGuard) arr[i][j] = 3;
        }

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (arr[i][j] == 0) count++;

        return count;
    }
}