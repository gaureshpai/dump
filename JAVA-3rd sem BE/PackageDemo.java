// Main program outside the mypack folder
import mypack.MyPackageClass;
//import mypack.*;


public class PackageDemo {
    public static void main(String[] args) {
        // Creating an instance of MyPackageClass from the mypack package
        MyPackageClass myPackageObject = new MyPackageClass();

        // Calling the displayMessage method from MyPackageClass
        myPackageObject.displayMessage();

        // Using the utility method addNumbers from MyPackageClass
        int result = MyPackageClass.addNumbers(5, 3);
        System.out.println("Result of adding numbers: " + result);
    }
}
