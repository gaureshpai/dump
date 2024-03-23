
class box{
    double width;
    double height;
    double depth;
    box(double w,double h,double d){
        width = w;
        height = h;
        depth = d;
    }
    double Volume{
        return width*depth*height;
    }
}
class bob{
    public static void main(String args[]){
        Box mybox1 = new Box(10,20,15);
        Box mybox2 = new Box(3,6,9);
        double vol;
        vol = mybox1.Volume();
        System.out.println("Volume is "+vol);
        vol = mybox2.Volume();
        System.out.println("Volume is "+vol);
    }
}