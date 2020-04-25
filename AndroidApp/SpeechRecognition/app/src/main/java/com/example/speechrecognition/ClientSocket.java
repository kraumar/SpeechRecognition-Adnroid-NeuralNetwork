package com.example.speechrecognition;

import android.os.StrictMode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
    static  Socket s;

    public void run(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

