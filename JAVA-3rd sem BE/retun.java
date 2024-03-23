
class box{
    double width;
    double height;
    double depth;
    double Volume{
        return width*depth*height;
    }
}
class retun{
public static void main(String args[]){
    box mybox = new box();
    double Volume;
    mybox.width=10;mybox.height=20;mybox.depth=15;
    Volume = mybox.Volume();
    System.out.println("Volume is "+Volume);
}
}