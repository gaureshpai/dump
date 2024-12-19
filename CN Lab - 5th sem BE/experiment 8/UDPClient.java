package program4;
import java.io.*;
import java.net.*;

public class UDPClient {
	public static void main(String args[]) throws Exception{
		BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket=new DatagramSocket();
		InetAddress IPAddress=InetAddress.getByName("Local host");

		byte[] sendData=new byte[1024];
		byte[] receiveData=new byte[1024];

		System.out.println("Enter start to connect to server");
		String sentence =inFromUser.readLine();
		sendData=sentence.getBytes();

		DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,9876);
		clientSocket.send(sendPacket);

		DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
		clientSocket.receive(receivePacket);

		String modifiedsentence=new String(receivePacket.getData());
		System.out.println("Message received from server:" +modifiedsentence);
		clientSocket.close();	
	}
}