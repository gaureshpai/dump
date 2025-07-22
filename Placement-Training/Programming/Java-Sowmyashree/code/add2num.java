class add2{
    public static int add(int num1, int num2){
        return (num1+num2);
    }
}

public class add2num{
    public static void main(String[] args){
        add2 obj = new add2();
        System.out.println(obj.add(5,10));
    }
}