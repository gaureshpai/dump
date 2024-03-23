
import java.util.Scanner;
class large{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the two numbers:");
        float a = obj.nextFloat();
        float b = obj.nextFloat();
        if(a>b){
        System.out.print("The first number is larger than second");   
        }else if(a<b){    
        System.out.print("The Second number is larger than first");
        }else{       
        System.out.print("Both numbers seem to be identical");
        }
    }
}