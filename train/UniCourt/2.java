import java.util.*;

public class Solution {

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return r == p.r && c == p.c;
        }

        @Override
        public int hashCode() { return Objects.hash(r, c); }
    }

    static class BFSResult {
        int[][] dist;
        Point[][] parent;
        BFSResult(int[][] d, Point[][] p) { dist = d; parent = p; }

        List<Point> buildPath(Point target) {
            List<Point> path = new ArrayList<>();
            Point cur = target;
            while (cur != null) {
                path.add(cur);
                cur = parent[cur.r][cur.c];
            }
            Collections.reverse(path);
            return path;
        }
    }

    private static BFSResult bfs(int[][] grid, Point start) {
        int h = grid.length, w = grid[0].length;
        int[][] dist = new int[h][w];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dist[start.r][start.c] = 0;

        Point[][] parent = new Point[h][w];
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int[] d : dirs) {
                int nr = cur.r + d[0], nc = cur.c + d[1];
                if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                if (dist[nr][nc] != Integer.MAX_VALUE / 2) continue;
                
                dist[nr][nc] = dist[cur.r][cur.c] + 1;
                parent[nr][nc] = cur;
                q.add(new Point(nr, nc));
            }
        }
        return new BFSResult(dist, parent);
    }

    public static int[][] solve(int[][] inputGrid) {
        int h = inputGrid.length, w = inputGrid[0].length;

        List<Point> houses = new ArrayList<>();
        Point postOffice = null;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (inputGrid[i][j] == 2) {
                    if (postOffice == null) postOffice = new Point(i, j);
                    else houses.add(new Point(i,j));
                }
                if (inputGrid[i][j] == 1) houses.add(new Point(i, j));
            }
        }
        if (postOffice == null) return inputGrid;

        List<Point> nodes = new ArrayList<>();
        nodes.add(postOffice);
        nodes.addAll(houses);
        int n = nodes.size();
        if (n <= 1) return inputGrid;

        int[][] dist = new int[n][n];
        List<List<List<Point>>> paths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            paths.add(new ArrayList<>());
            for (int j = 0; j < n; j++) paths.get(i).add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            BFSResult res = bfs(inputGrid, nodes.get(i));
            for (int j = 0; j < n; j++) {
                dist[i][j] = res.dist[nodes.get(j).r][nodes.get(j).c];
                paths.get(i).set(j, res.buildPath(nodes.get(j)));
            }
        }

        int[][] dp = new int[1 << n][n];
        int[][] parent = new int[1 << n][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0 || dp[mask][u] >= Integer.MAX_VALUE / 2) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0 && dist[u][v] < Integer.MAX_VALUE / 2) {
                        int newMask = mask | (1 << v);
                        int newCost = dp[mask][u] + dist[u][v];
                        if (newCost < dp[newMask][v]) {
                            dp[newMask][v] = newCost;
                            parent[newMask][v] = u;
                        }
                    }
                }
            }
        }

        int finalMask = (1 << n) - 1;
        int bestEnd = -1;
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[finalMask][i] < minCost) {
                minCost = dp[finalMask][i];
                bestEnd = i;
            }
        }

        if (bestEnd == -1) return inputGrid;

        List<Integer> order = new ArrayList<>();
        int curr = bestEnd;
        int mask = finalMask;
        while (curr != 0) {
            order.add(curr);
            int prevMask = mask ^ (1 << curr);
            curr = parent[mask][curr];
            mask = prevMask;
        }
        order.add(0);
        Collections.reverse(order);

        int[][] output = new int[h][w];
        for (int i = 0; i < h; i++) output[i] = Arrays.copyOf(inputGrid[i], w);

        for (int i = 0; i < order.size() - 1; i++) {
            List<Point> p = paths.get(order.get(i)).get(order.get(i + 1));
            for (Point pt : p) {
                if (output[pt.r][pt.c] == 0) output[pt.r][pt.c] = 3;
            }
        }

        return output;
    }
}