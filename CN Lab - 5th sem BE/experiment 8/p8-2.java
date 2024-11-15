import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        
        InetAddress IPAddress = InetAddress.getByName("localhost");
        
        byte[] sendData = new byte[1024];
        byte[] receivedData = new byte[1024];
        
        System.out.println("Enter 'start' to connect to server:");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        
        // Create and send packet to server
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        
        // Create a packet for receiving server's response
        DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
        clientSocket.receive(receivePacket);
        
        // Properly convert received data to string
        String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Message received from Server: " + modifiedSentence);
        
        // Close the client socket
        clientSocket.close();
    }
}
