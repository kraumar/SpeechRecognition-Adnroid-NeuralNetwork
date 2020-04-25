import java.io.*;  
import java.net.*; 
import java.lang.*;

public class client {

public static void main(String[] args) {  



    try{      
        Socket socket=new Socket("192.168.43.53",2004);  

        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());  
        DataInputStream din=new DataInputStream(socket.getInputStream());


        dout.writeUTF("Hello");
        dout.flush();

        System.out.println("send first mess");
        String str = din.readUTF();//in.readLine();

        System.out.println("Message"+str);


        dout.close();  
        din.close();
        socket.close();
        }

    catch(Exception e){
        e.printStackTrace();}   


}  

}