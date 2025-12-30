import java.net.*;

public class DatagramClient{
        public static void main(String args[]){
                try{
                        DatagramSocket clientSocket=new DatagramSocket(9876);
                        byte[] receivedata=new byte[1024];
                        System.out.println("Cient is running Waiting for the messages from the server");
                        while(true){
                                DatagramPacket receivePacket=new DatagramPacket(receivedata,receivedata.length);
                                clientSocket.receive(receivePacket);
                                String message=new String(receivePacket.getData(),0,receivePacket.getLength());
                                System.out.println("Message from server "+ message);
                                if(message.equalsIgnoreCase("exit")){
                                        System.out.println("Client Shutting down");
                                        break;
                                }}
                        clientSocket.close();
                }
                catch(Exception e){
                        e.printStackTrace();
                }}}
~
~
~
