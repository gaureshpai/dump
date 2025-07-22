class Human{
    public String name;
    public int age;
    public int phoneNum;

    public Human(){
        this.name = "mr";
        this.age = 0;
        this.phoneNum = 0000;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }
    
    public int getPhoneNum(){
        return this.phoneNum;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setphoneNum(int phoneNum){
        this.phoneNum = phoneNum;
    }
}

public class DemoEncap {
    public static void main(String[] args){
        Human obj = new Human();
        obj.name = "gauresh";
        obj.age = 21;
        obj.phoneNum = 1234;
        System.out.println(obj.getName());
        System.out.println(obj.getAge());
        System.out.println(obj.getPhoneNum());

        System.out.println("After Change");

        obj.setAge(20);
        obj.setname("hehe");
        obj.setphoneNum(4321);        
        System.out.println(obj.getName());
        System.out.println(obj.getAge());
        System.out.println(obj.getPhoneNum());
    }
}