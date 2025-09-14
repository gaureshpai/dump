import java.util.*;

public class Sol1 {
    // All 8 possible knight moves
    static int[][] moves = {
        {2, 1}, {2, -1}, {1, 2}, {1, -2},
        {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}
    };

    public static int BlackKnight(int x, int y, int a, int b) {
        // Validate inputs
        if (x < 0 || y < 0 || x >= 8 || y >= 8) {
            System.out.println("Enter a valid knight position");
            return -1;
        }
        if (a < 0 || b < 0 || a >= 8 || b >= 8) {
            System.out.println("Enter a valid queen position");
            return -1;
        }
        if (x == a && y == b) {
            System.out.println("Knight and Queen cannot be on the same square");
            return -1;
        }
        if (b == 7) {
            System.out.println("The queen cannot be in the topmost row");
            return -1;
        }

        // Mark queen’s attack squares
        boolean[][] blocked = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            blocked[a][i] = true; // row
            blocked[i][b] = true; // column
        }
        for (int i = -7; i <= 7; i++) {
            if (a + i >= 0 && a + i < 8 && b + i >= 0 && b + i < 8)
                blocked[a + i][b + i] = true;
            if (a + i >= 0 && a + i < 8 && b - i >= 0 && b - i < 8)
                blocked[a + i][b - i] = true;
        }

        // BFS for shortest path
        Queue<int[]> q = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        boolean[][] visited = new boolean[8][8];

        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            // Success condition: reached top row
            if (cy == 7) {
                List<int[]> path = new ArrayList<>();
                String key = cx + "," + cy;
                while (parent.containsKey(key)) {
                    String[] parts = key.split(",");
                    path.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])});
                    key = parent.get(key);
                }
                path.add(new int[]{x, y});
                Collections.reverse(path);

                for (int[] p : path) {
                    System.out.print("(" + p[0] + "," + p[1] + ") -> ");
                }
                System.out.println();
                return path.size() - 1; // moves count
            }

            for (int[] m : moves) {
                int nx = cx + m[0], ny = cy + m[1];
                if (nx >= 0 && ny >= 0 && nx < 8 && ny < 8 && !blocked[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    parent.put(nx + "," + ny, cx + "," + cy);
                }
            }
        }

        System.out.println("No path to the top row without passing through queen’s guarded squares.");
        return -1;
    }

    public static void main(String[] args) {
        int x = 5, y = 3; // knight position
        int a = 6, b = 0; // queen position
        int res = BlackKnight(x, y, a, b);
        if (res != -1)
            System.out.println("Minimum Moves: " + res);
    }
}

/*
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
*/