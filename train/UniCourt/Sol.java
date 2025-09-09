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
}