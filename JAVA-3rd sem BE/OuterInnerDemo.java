class Outer {
    void display() {
        System.out.println("Outer class display method");
    }

    class Inner {
        void display() {
            System.out.println("Inner class display method");
        }
    }
}

public class OuterInnerDemo {
    public static void main(String[] args) {
        // Create an instance of the Outer class
        Outer outer = new Outer();

        // Call the display method of the Outer class
        outer.display();

        // Create an instance of the Inner class (nested inside Outer)
        Outer.Inner inner = outer.new Inner();

        // Call the display method of the Inner class
        inner.display();
    }
}
