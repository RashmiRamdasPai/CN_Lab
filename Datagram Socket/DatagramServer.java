import java.util.*;
import java.net.*;
import java.io.*;

public class DatagramServer{
        public static void main(String args[]){
                Scanner sc=new Scanner(System.in);
                try{
                        DatagramSocket serverSocket=new DatagramSocket();
                        InetAddress clientAddress =InetAddress.getByName("localhost");
                        int clientPort=9876;
                        System.out.println("Server started.Type messages to send to the client");
                        System.out.println("Type exit to quit");
                        while(true){
                                System.out.println("Server");
                                String message=sc.nextLine();
                                byte[] sendData=message.getBytes();
                                DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,clientAddress,clientPort);
                                serverSocket.send(sendPacket);
                                if(message.equalsIgnoreCase("exit")){
                                        System.out.println("Shutting server down.");
                                        break;
                                }}
                        serverSocket.close();
                        sc.close();
                }
                catch(Exception e){
                        e.printStackTrace();
                }}}
~
~
~
~
~
~
