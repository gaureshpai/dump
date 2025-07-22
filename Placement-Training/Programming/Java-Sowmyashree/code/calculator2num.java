class calc{
    public static int add(int num1, int num2){
        return (num1+num2);
    }
    public static int subtract(int num1, int num2){
        return (num1-num2);
    }
}

class calci extends calc{
    public static int mul(int num1, int num2){
        return (num1*num2);
    }
    public static int div(int num1, int num2){
        return (num1/num2);
    }
} 

public class calculator2num{
    public static void main(String[] args){
        calci obj = new calci();
        System.out.println("Sum:"+obj.add(5,10));
        System.out.println("Difference:"+obj.subtract(5,10));
        System.out.println("Product:"+obj.mul(5,10));
        System.out.println("Coefficient:"+obj.div(5,10));
    }
}