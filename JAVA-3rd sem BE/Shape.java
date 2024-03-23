class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    public void draw() {
        System.out.println("Drawing a " + name);
    }

    public void erase() {
        System.out.println("Erasing a " + name);
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }

    @Override
    public void erase() {
        System.out.println("Erasing a circle with radius " + radius);
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String name, double base, double height) {
        super(name);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
    }

    @Override
    public void erase() {
        System.out.println("Erasing a triangle with base " + base + " and height " + height);
    }
}

class Square extends Shape {
    private double side;

    public Square(String name, double side) {
        super(name);
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a square with side length " + side);
    }

    @Override
    public void erase() {
        System.out.println("Erasing a square with side length " + side);
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];

        shapes[0] = new Circle("Circle", 5.0);
        shapes[1] = new Triangle("Triangle", 4.0, 6.0);
        shapes[2] = new Square("Square", 3.0);

        for (Shape shape : shapes) {
            shape.draw();
            shape.erase();
            System.out.println();
        }
    }
}
