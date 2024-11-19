// client program

import java.io.*;
import java.net.*;

public class TCPC{
    public static void main(String[] args) thorws Exception{
        Socket sersock = new Socket("1f27.0.01",4000);
        System.out.println("Enter the filename");
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = fRead.readLine();
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream,true);
        pwrite.println(fname);
        InputStream istream =sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
        String str;
        while((str = socketRead.readLine())!= null){
            pwrite.println(str);
        }
        sock.close();
        socketRead.close();
        keyRead.close();
    }
}