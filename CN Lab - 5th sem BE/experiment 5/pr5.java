import java.util.Scanner; 
 
public class Bellman{ 
    public static void bellmanFord(int numvertex,int source,int numedges,int edgemat[][]){ 
        int srcdist[]=new int[numvertex+1]; 

        for(int i=1;i<=numvertex;i++){ 
            srcdist[i]=999; 
        } 
        srcdist[source]=0; 

        for(int i=1;i<=numvertex;i++){ 
            for(int j=1;j<=numedges;j++){ 
                int u=edgemat[j][0]; 
                int v=edgemat[j][1]; 
                int w=edgemat[j][2]; 

                if(srcdist[v]>srcdist[u]+w){ 
                    srcdist[v]=srcdist[u]+w; 
                } 
            } 
        } 
        for(int i=1;i<=numvertex;i++){ 
            System.out.println("Distance from Node "+source+"to node"+i+"is" +srcdist[i]); 
        } 
    } 
    
    public static void main(String args[]){ 
        int numvertex=0; 
        int source; 
        int numedges=0; 

        Scanner scanner=new Scanner(System.in); 
        System.out.print("enter the number of vertices:"); 
        numvertex=scanner.nextInt(); 

        System.out.print("enter the number of edges:"); 
        numedges=scanner.nextInt(); 

        int edgemat[][]=new int[numedges+1][3]; 
        System.out.println("enter the edge matrix:[from,to,distance]"); 

        for(int i=1;i<=numedges;i++){ 
            edgemat[i][0]=scanner.nextInt(); 
            edgemat[i][1]=scanner.nextInt(); 
            edgemat[i][2]=scanner.nextInt(); 
        } 

        System.out.print("enter the source vertex:"); 
        source=scanner.nextInt(); 
        
        bellmanFord(numvertex,source,numedges,edgemat); 
    } 
} 