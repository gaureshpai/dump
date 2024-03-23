
import java.util.Scanner;
class switch1{
    public static void main(String []args){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the month:");
        int m = obj.nextInt();
        switch(m){
            case 12:
            case 1:
            case 2:System.out.println("The season is winter");break;
            case 3:
            case 4:
            case 5:System.out.println("The season is summer");break;
            case 6:
            case 7:
            case 8:System.out.println("The season is rainy");break;
            case 9:
            case 10:
            case 11:System.out.println("The season is Autumn");break;
            default:System.out.println("Invalid");break;
        }
    }
}