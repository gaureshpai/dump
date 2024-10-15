import java.util.Scanner;

class crc{ 
    public static void main(String args[]){ 
        int i,j,k; 
        int[] r; 
        int[] z; 
        Scanner sc=new Scanner(System.in); 
        System.out.print("enter no.of Data Bit:"); 
        int n=sc.nextInt(); 

        System.out.println("enter the no.of generator Bits:"); 
        int m=sc.nextInt(); 

        int[] d=new int[n+m]; 
        int[] g=new int[m]; 

        System.out.println("Enter the Data Bits"); 
        for(i=0;i<n;i++) 
            d[i]=sc.nextInt(); 

        System.out.println("Enter the Generator Bits"); 
        for(j=0;j<m;j++) 
            g[j]=sc.nextInt();

        for(i=0;i<m-1;i++) 
            d[n+i]=0; 
        r=new int[m+n]; 
        z=new int[m]; 
        for(i=0;i<m;i++){   
            r[i]=d[i]; 
            z[i]=0; 
        }  
        for(i=0;i<n;i++){ 
            k=0; 
            int msb=r[i]; 
            for(j=i;j<m+i;j++){ 
                if(msb==0) 
                    r[j]=xor(r[j],z[k]); 
                else 
                    r[j]=xor(r[j],g[k]); 
                k++; 
            } 
            r[m+i]=d[m+i]; 
        } 
        System.out.print("the Code bit added : "); 
        for(i=n;i<n+m-1;i++){ 
            d[i]=r[i]; 
            System.out.print(d[i]); 
        } 

        System.out.println("the Code Data is : "); 
        for(i=0;i<n+m-1;i++) 
            System.out.print(d[i]); 
    } 
    
    public static int xor(int x,int y){ 
        if(x==y) 
            return(0); 
        else 
            return(1); 
    } 
} 