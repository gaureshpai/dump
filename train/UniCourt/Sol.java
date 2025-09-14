import java.util.*;

public class Sol {
    static int[][] moves = {
        {2, 1}, {2, -1}, {1, 2}, {1, -2}
    };

    public static int BlackKnight(int x, int y) {
        if (x < 0 || y < 0 || x >= 8 || y >= 8) {
            System.out.println("Enter a valid input");
            return -1;
        }

        List<int[]> path = new ArrayList<>();
        path.add(new int[]{x, y});
        int res = 0;

        boolean moved;
        while (true) {
            moved = false;
            for (int[] move : moves) {
                int newX = x + move[0];
                int newY = y + move[1];
                if (newX >= 0 && newY >= 0 && newX < 8 && newY < 8) {
                    x = newX;
                    y = newY;
                    path.add(new int[]{x, y});
                    res++;
                    moved = true;
                    break; // always prefer the first valid move
                }
            }
            if (!moved) break;
        }

        // Print path
        for (int[] p : path) System.out.print("(" + p[0] + "," + p[1] + ") -> ");
        System.out.println();
        return res;
    }

    public static void main(String[] args) {
        int x = 2, y = 2;
        int res = BlackKnight(x, y);
        if (res != -1)
            System.out.println("Total Moves: " + res);
    }
}

/*
// import Java.util.*;
// import Java.util.Scanner;
// import Java.util.ArrayList;
// import java.util.Arrays;

public class Sol{
    public static int BlackKnight(int x, int y){
        if(x >= 8 || y >= 8 || x < 0 || y < 0) {System.out.println("Enter a valid input"); return -1;}
        // if(y == 7) {System.out.println("Already in the last row"); return 0;}
        
        int res = 0, i = 0;
        // List<List<Integer>> path = new ArrayList<>();
        int[][] xy = new int[7][2];
        xy[0][0] = y;
        xy[0][1] = x;
        i++;

        while(true){
            if(x + 2 < 8 && y + 1 < 8){
                x= x+ 2;
                y = y + 1;
                res++;
            } else if(x+ 2 < 8 && y - 1 >= 0){
                x= x+ 2;
                y = y - 1;
                res++;
            } else if(x+ 1 < 8 && y + 2 < 8){
                x= x+ 1;
                y = y + 2;
                res++;
            } else if(x+ 1 < 8 && y - 2 >= 0){
                x= x+ 1;
                y = y - 2;
                res++;
            } else{
                for(i = 0; i < xy.length; i++)
                    if(xy[i][0] != 0 && xy[i][1] != 0)
                        System.out.print("("+ xy[i][1] + "," + xy[i][0] + ") -> ");
                    else{
                        System.out.println();
                        return res;
                    }
            };
            
            xy[i][0] = x;
            xy[i][1] = y;
            i++;
        }
    }

    public static void main(String[] args){
        int x = 2;
        int y = 2;
        int res =  BlackKnight(x,y);
        if(res != -1)
            System.out.println("Total Moves: "+ res);
    }
} */