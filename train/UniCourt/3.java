//self done

import java.util.*;

public class Solution {
    public static int[][] solve(int[][] inputGrid) {
        /*
         * Transform the input grid according to the pattern.
         * 
         * Args:
         *     inputGrid: 2D array of integers representing the input grid
         *         0: Black     1: Blue       2: Red        3: Green     4: Yellow
         *         5: Grey      6: Fuchsia    7: Orange     8: Teal      9: Brown
         *         
         * Returns:
         *     2D array of integers representing the transformed grid
         */
        
        // Get grid dimensions
        int height = inputGrid.length;
        int width = inputGrid[0].length;
        
        // Create output grid with same dimensions
        int[][] outputGrid = new int[height][width];

        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                if(inputGrid[i][j] != 1)
                    outputGrid[i][j] = inputGrid[i][j];
                else
                    outputGrid[i][j] = 0;

        
        // TODO: Implement your solution here
        // Study the examples to understand the pattern
        
        return outputGrid;
    }
}