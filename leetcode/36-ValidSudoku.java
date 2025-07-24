/*
Question:
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
- Each row must contain the digits 1-9 without repetition.
- Each column must contain the digits 1-9 without repetition.
- Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.
*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                char ele = board[i][j];
                if (ele != '.') {
                    for (int k = 0; k < board.length; k++) {
                        if (k != i && ele == board[k][j]) return false;
                        if (k != j && ele == board[i][k]) return false;
                    }
                    int boxRowStart = (i / 3) * 3;
                    int boxColStart = (j / 3) * 3;
                    for (int row = boxRowStart; row < boxRowStart + 3; row++) {
                        for (int col = boxColStart; col < boxColStart + 3; col++) {
                            if (row == i && col == j) continue;
                            if (board[row][col] == ele) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}