/*
Question:
You are given a 2D binary array grid. 
Find a rectangle with horizontal and vertical sides with the smallest area, such that all the 1's in grid lie inside this rectangle.
Return the minimum possible area of the rectangle.
*/

class Solution {
    public int minimumArea(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int right = 0, left = 0, top = 0, bottom = 0;
        int flag = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
            if (grid[i][j] == 1) {
                right = i;
                flag = 1;
                break;
            }
            if (flag == 1) break;
        }

        flag = 0;
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++)
            if (grid[i][j] == 1) {
                left = i;
                flag = 1;
                break;
            }
            if (flag == 1) break;
        }

        flag = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++)
            if (grid[j][i] == 1) {
                top = i;
                flag = 1;
                break;
            }
            if (flag == 1) break;
        }

        flag = 0;
        for (int i = cols - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++)
            if (grid[j][i] == 1) {
                bottom = i;
                flag = 1;
                break;
            }
            if (flag == 1) break;
        }

        return (bottom - top + 1) * (left - right + 1);
    }
}

/*
First Thought:
class Solution {
    public int minimumArea(int[][] grid) {
        int top = grid.length, bottom = -1;
        int left = grid[0].length, right = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i < top) top = i;
                    if (i > bottom) bottom = i;
                    if (j < left) left = j;
                    if (j > right) right = j;
                }
            }
        }

        if (bottom == -1) return 0;

        return (bottom - top + 1) * (right - left + 1);
    }
}
*/