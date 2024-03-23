
class box{
    double width;
    double height;
    double depth;
    box(double w,double h,double d){
        width = w;
        height = h;
        depth = d;
    }
    box(){
        width = -1;
        height = -1;
        depth = -1;
    }
    box(double len){
        width = height = depth = len;
    }
    double Volume(){
        return width*height*depth;
    }
}
class overloadcons{
    public static void main(String args[]){
        box mybox1 = new box(10,20,15);
        box mybox2 = new box(10);
        box mybox3 = new box();
        double vol;
        vol = mybox1.vol();
        System.out.println("Volume of mybox1 is: "+vol);
        vol = mybox2.vol();
        System.out.println("Volume of mybox2 is: "+vol);
        vol = mybox3.vol();
        System.out.println("Volume of mybox3 is: "+vol);
    }
    
}