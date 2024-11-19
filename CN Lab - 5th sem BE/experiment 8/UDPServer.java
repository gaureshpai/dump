//server program

import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPServer {
    public static void main(String args[]) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Server Started");
        
        // Receive data from the client
        byte[] receivedData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
        serverSocket.receive(receivePacket);
        
        String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received from client: " + clientMessage);
        
        InetAddress clientIPAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        System.out.println("Client Connected");

        // Prepare to send a response to the client
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the message to be sent:");
        String message = input.nextLine();
        input.close();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
        serverSocket.send(sendPacket);

        System.out.println("Message sent to client");
        serverSocket.close(); // Properly close the server socket
    }
}