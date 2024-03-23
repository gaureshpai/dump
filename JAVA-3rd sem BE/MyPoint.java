public class MyPoint {
    private int x;
    private int y;

    // Default constructor
    public MyPoint() {
        this.x = 0;
        this.y = 0;
    }

    // Overloaded constructor
    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Set both x and y
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Get x and y in a 2-element int array
    public int[] getXY() {
        return new int[]{x, y};
    }

    // Return a string description of the instance in the format "(x, y)"
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Calculate distance from this point to another point at (x, y) coordinates
    public double distance(int x, int y) {
        int xDiff = this.x - x;
        int yDiff = this.y - y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    // Calculate distance from this point to another MyPoint instance (another)
    public double distance(MyPoint another) {
        return distance(another.x, another.y);
    }

    // Calculate distance from this point to the origin (0,0)
    public double distance() {
        return distance(0, 0);
    }
}
