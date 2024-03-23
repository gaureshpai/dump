
import java.util.Scanner;
class month{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the month number:");
        int m = obj.nextInt();
        if(m==12||m==1||m==2){
        System.out.println("the season is winter");
        }
        else if(m==3||m==4||m==5){
            System.out.println("the season is summer");
        }
        else if(m==6||m==7||m==8){
                System.out.println("the season is rainy");
        }
        else if(m==9||m==10||m==11){
                    System.out.println("the season is Autumn");
        }else{ 
            System.out.println("the season is Invalid");
        }
    }
}