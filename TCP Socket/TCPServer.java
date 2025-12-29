import java.io.*;
import java.net.*;

public class TCPServer{
        public static void main(String args[]) throws Exception{
                ServerSocket sersock=new ServerSocket(3300);
                Socket sock=sersock.accept();
                InputStream istream=sock.getInputStream();
                BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream));
                String fname=fileRead.readLine();
                BufferedReader contentRead=new BufferedReader(new FileReader(fname));
                OutputStream ostream=sock.getOutputStream();
                PrintWriter pwrite=new PrintWriter(ostream,true);
                String str;
                while((str=contentRead.readLine())!=null) pwrite.println(str);
                sock.close();
                sersock.close();
                pwrite.close();
                contentRead.close();
                fileRead.close();
        }}
  
