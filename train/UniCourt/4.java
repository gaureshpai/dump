import java.util.*;

class Solution {

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return r == point.r && c == point.c;
        }
        
        @Override
        public int hashCode() {
            return r * 31 + c;
        }
    }
    
    public static int[][] solve(int[][] grid) {
        int h = grid.length, w = grid[0].length;
        int[][] out = new int[h][w];
        
        // Copy the original grid
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                out[i][j] = grid[i][j];
            }
        }
        
        // Process each building to extend its territory
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] > 1) {
                    extendTerritory(grid, out, i, j, grid[i][j]);
                }
            }
        }
        
        return out;
    }
    
    // Extend territory for a building from its position
    private static void extendTerritory(int[][] grid, int[][] out, int startR, int startC, int buildingId) {
        int h = grid.length, w = grid[0].length;
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        // Find all cells of this building (in case it's multi-cell)
        Set<Point> buildingCells = new HashSet<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == buildingId) {
                    buildingCells.add(new Point(i, j));
                }
            }
        }
        
        // Extend into adjacent empty cells (1s)
        for (Point cell : buildingCells) {
            for (int[] dir : dirs) {
                int nr = cell.r + dir[0];
                int nc = cell.c + dir[1];
                
                if (nr >= 0 && nr < h && nc >= 0 && nc < w && grid[nr][nc] == 1) {
                    // Only claim if not already claimed by a smaller building ID
                    if (out[nr][nc] == 1 || (out[nr][nc] > 1 && buildingId < out[nr][nc])) {
                        out[nr][nc] = buildingId;
                    }
                }
            }
        }
        
        // Look for territorial expansion across walls (special pattern)
        expandAcrossWalls(grid, out, buildingCells, buildingId);
    }
    
    // Handle special territorial expansion across walls
    private static void expandAcrossWalls(int[][] grid, int[][] out, Set<Point> buildingCells, int buildingId) {
        int h = grid.length, w = grid[0].length;
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        // Look for patterns where the building can extend across walls
        for (Point cell : buildingCells) {
            for (int[] dir : dirs) {
                // Check for extension pattern: building -> wall(s) -> empty cells
                int steps = 1;
                while (steps <= 3) { // Max steps to look ahead
                    int nr = cell.r + dir[0] * steps;
                    int nc = cell.c + dir[1] * steps;
                    
                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) break;
                    
                    // If we hit an empty cell after crossing walls
                    if (grid[nr][nc] == 1) {
                        // Check if there's a clear path or if this forms a territorial extension
                        if (shouldClaimAcrossWalls(grid, cell.r, cell.c, nr, nc, buildingId)) {
                            if (out[nr][nc] == 1 || (out[nr][nc] > 1 && buildingId < out[nr][nc])) {
                                out[nr][nc] = buildingId;
                            }
                        }
                        break;
                    } else if (grid[nr][nc] > 1 && grid[nr][nc] != buildingId) {
                        break; // Hit another building
                    }
                    steps++;
                }
            }
        }
    }
    
    // Check if we should claim a cell across walls
    private static boolean shouldClaimAcrossWalls(int[][] grid, int fromR, int fromC, int toR, int toC, int buildingId) {
        // For now, allow claiming across walls if distance is small
        int distance = Math.abs(fromR - toR) + Math.abs(fromC - toC);
        return distance <= 3;
    }
}