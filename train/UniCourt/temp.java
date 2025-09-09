// import Java.util.*;
// import Java.util.Scanner;
// import Java.util.ArrayList;
// import java.util.Arrays;

public class Sol{
    public static int BlackKnight(int x, int y){
        if(x> 8 || y > 8) System.out.println("Enter a valid input");
        int X = x, Y = y;
        
        int res = 0, i = 0;
        // List<List<Integer>> path = new ArrayList<>();
        int[][] xy = new int[7][2];

        while(true){
            if(X + 2 < 8 && Y + 1 < 8){
                X= X+ 2;
                Y = Y + 1;
                res++;
            } else if(X+ 2 < 8 && Y - 1 >= 0){
                X= X+ 2;
                Y = Y - 1;
                res++;
            } else if(X+ 1 < 8 && Y + 2 < 8){
                X= X+ 1;
                Y = Y + 2;
                res++;
            } else if(X+ 1 < 8 && Y - 2 >= 0){
                X= X+ 1;
                Y = Y - 2;
                res++;
            } else{
                for(i = 0; i < xy.length; i++)
                    if(xy[i][0] != 0 && xy[i][1] != 0)
                        System.out.print("("+ xy[i][0] + "," + xy[i][1] + ") -> ");
                    else{
                        System.out.println();
                        return res;
                    }
            };
            
            xy[i][0] = X;
            xy[i][1] = Y;
            i++;
        }
    }

    public static void main(String[] args){
        int x= 2;
        int y = 2;
        int res =  BlackKnight(x,y);
        System.out.println("Total Moves: "+ res);
    }
}