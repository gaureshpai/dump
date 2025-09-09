// import Java.util.*;
// import Java.util.Scanner;
// import Java.util.ArrayList;
// import java.util.Arrays;

public class Sol1 {
    public static int BlackKnight(int x, int y, int a, int b) {
        if (x >= 8 || y >= 8 || x < 0 || y < 0) { System.out.println("Enter a valid input"); return -1; }
        if (x == b && y == b) { System.out.println("Both knight and queen are placed in the same square. Cannot move to top"); return -1; }
        if (b == 7) { System.out.println("The queen can not be placed in the top most row"); return -1; }

        int res = 0, w = 0;
        int[][] grid = new int[8][8];
        grid[a][b] = 5;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == a || j == b || i - a == j - b) grid[i][j] = 5;
            }

        }

        int[][] xy = new int[7][2];
        grid[x][y] = 1;
        int p = 0, q = 0;
        int i =0, j = 0;

            while (true) {
                if (x + 2 < 8 && y + 1 < 8 && grid[x + 2][y + 1] != 5) {
                    x = x + 2;
                    y = y + 1;
                    res++;
                    xy[w][0] = x;
                    xy[w][1] = y;
                    w++;
                    grid[x][y] = 1;
                    if(i < 7) i++;
                    if(j < 7) j++;
                } else if (x + 2 < 8 && y - 1 >= 0 && grid[x + 2][y - 1] != 5) {
                    x = x + 2;
                    y = y - 1;
                    res++;
                    xy[w][0] = x;
                    xy[w][1] = y;
                    w++;
                    grid[x][y] = 1;
                    if(i < 7) i++;
                    if(j < 7) j++;
                } else if (x + 1 < 8 && y + 2 < 8 && grid[x + 1][y + 2] != 5) {
                    x = x + 1;
                    y = y + 2;
                    res++;
                    xy[w][0] = x;
                    xy[w][1] = y;
                    w++;
                    grid[x][y] = 1;
                    if(i < 7) i++;
                    if(j < 7) j++;
                // } else if(x - 1 >= 0 && y - 2 >= 0 && grid[x - 1][y - 2] != 5){
                //     x= x - 1;
                //     y = y - 2;
                //     res++;
                //     xy[w][0] = x;
                //     xy[w][1] = y;
                //     w++;
                //     if(i < 7) i++;
                //     if(j < 7) j++;
                // } else if(x - 1 >= 0 && y + 2 < 8 && grid[x - 1][y + 2] != 5){
                //     x= x - 1;
                //     y = y + 2;
                //     res++;
                //     xy[w][0] = x;
                //     xy[w][1] = y;
                //     w++;
                //     if(i < 7) i++;
                //     if(j < 7) j++;
                // } else if(x - 2 >= 0 && y - 1 >= 0 && grid[x - 2][y - 1] != 5){
                //     x= x - 2;
                //     y = y - 1;
                //     res++;
                //     xy[w][0] = x;
                //     xy[w][1] = y;
                //     w++;
                //     if(i < 7) i++;
                //     if(j < 7) j++;
                // } else if(x - 2 >= 0 && y + 1 < 8 && grid[x - 2][y + 1] != 5){
                //     x= x - 2;
                //     y = y + 1;
                //     res++;
                //     xy[w][0] = x;
                //     xy[w][1] = y;
                //     w++;
                //     if(i < 7) i++;
                //     if(j < 7) j++;
                } else {
                    for (i = 0; i < xy.length; i++)
                        if (xy[i][0] != 0 && xy[i][1] != 0)
                            System.out.print("(" + xy[i][0] + "," + xy[i][1] + ") -> ");
                        else {
                            System.out.println();
                            return res;
                        }
                };
            }
    }

    public static void main(String[] args) {
        int x = 5, y = 3, a = 6, b = 0, res = BlackKnight(x, y, a, b);
        if (res != -1)
            System.out.print("Total Moves: " + res);
    }
}