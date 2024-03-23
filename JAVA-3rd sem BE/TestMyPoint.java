public class TestMyPoint {
    public static void main(String[] args) {
        // Creating MyPoint objects using different constructors
        MyPoint point1 = new MyPoint();
        MyPoint point2 = new MyPoint(3, 4);

        // Testing setXY and getXY methods
        point1.setXY(1, 2);
        System.out.println("Point1 coordinates after setXY: " + point1.getXY()[0] + ", " + point1.getXY()[1]);

        // Testing toString method
        System.out.println("Point2 coordinates: " + point2.toString());

        // Testing distance methods
        System.out.println("Distance from Point1 to Point2: " + point1.distance(point2));
        System.out.println("Distance from Point2 to Origin: " + point2.distance());
    }
}
