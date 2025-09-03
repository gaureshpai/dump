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

    public static int[][] solve(int[][] inputGrid) {
        int h = inputGrid.length, w = inputGrid[0].length;

        // Find post office and houses
        List<Point> nodes = new ArrayList<>();
        Point start = null;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (inputGrid[i][j] == 2) start = new Point(i, j);
                if (inputGrid[i][j] == 1) nodes.add(new Point(i, j));
            }
        }
        if (start == null) return inputGrid; // no post office found

        nodes.add(0, start); // index 0 = post office
        int n = nodes.size();

        // Precompute shortest paths
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

        // TSP with bitmask DP
        if (n > 20) throw new IllegalArgumentException("Too many houses for bitmask DP!");
        int[][] dp = new int[1 << n][n];

        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0) continue;
                    dp[mask | (1 << v)][v] = Math.min(dp[mask | (1 << v)][v],
                            dp[mask][u] + dist[u][v]);
                }
            }
        }

        // Reconstruct order safely
        int mask = (1 << n) - 1;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (dp[mask][i] < dp[mask][cur]) cur = i;
        }
        List<Integer> order = new ArrayList<>();
        while (mask > 0) {
            order.add(cur);
            int best = -1;
            for (int prev = 0; prev < n; prev++) {
                if ((mask & (1 << prev)) == 0) continue;
                if (dp[mask][cur] == dp[mask ^ (1 << cur)][prev] + dist[prev][cur]) {
                    best = prev;
                    mask ^= (1 << cur);
                    cur = prev;
                    break;
                }
            }
            if (best == -1) break;
        }
        Collections.reverse(order);

        // Build output
        int[][] output = new int[h][w];
        for (int i = 0; i < h; i++) output[i] = Arrays.copyOf(inputGrid[i], w);

        // Draw path
        for (int i = 0; i < order.size() - 1; i++) {
            List<Point> p = paths.get(order.get(i)).get(order.get(i + 1));
            for (Point pt : p) {
                if (output[pt.r][pt.c] == 0) output[pt.r][pt.c] = 3;
            }
        }

        return output;
    }

    static class BFSResult {
        int[][] dist;
        Map<Point, Point> parent;
        BFSResult(int[][] d, Map<Point, Point> p) { dist = d; parent = p; }

        List<Point> buildPath(Point target) {
            List<Point> path = new ArrayList<>();
            Point cur = target;
            while (parent.containsKey(cur)) {
                path.add(cur);
                cur = parent.get(cur);
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

        Map<Point, Point> parent = new HashMap<>();
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
                Point next = new Point(nr, nc);
                parent.put(next, cur);
                q.add(next);
            }
        }
        return new BFSResult(dist, parent);
    }
}
