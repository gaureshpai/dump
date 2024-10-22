import java.util.*; 

class prg7 { 
    public static void main(String args[]){ 
        
        Scanner sc=new Scanner(System.in); 

        int i,size,nop,opr,temp=0; 
        int[] datarate=new int[100];

        System.out.println("enter the bucket size"); 
        size=sc.nextInt(); 

        System.out.println("enter the number of packets"); 
        nop=sc.nextInt(); 

        System.out.println("enter the dara rate"); 
        
        for(i=0;i<nop;i++) 
            datarate[i]=sc.nextInt(); 
        
        System.out.println("enter the output rate"); 
        opr=sc.nextInt(); 
        
        for(i=0;i<nop;i++) { 
            if(datarate[i]>size) 
                System.out.println("bucket overflow"); 
            else { 
                temp=datarate[i]; 
            
                while(temp>opr){ 
                    System.out.println("packet transmission"+opr); 
                    temp=temp-opr; 
                }  
                System.out.println("packet transmission"+temp); 
            } 
        } 
    } 
} 