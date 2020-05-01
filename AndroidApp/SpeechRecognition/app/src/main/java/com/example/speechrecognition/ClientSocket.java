package com.example.speechrecognition;

import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket{

    Socket socket = null;

    public void send(String path){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            socket=new Socket("192.168.43.53",2004);
            File file = new File(path);
            byte[] bytes = new byte[8000];
            OutputStream out = socket.getOutputStream();
            InputStream in = new FileInputStream(file);

            int count;
            while((count = in.read(bytes)) > 0){
                out.write(bytes, 0, count);
            }
            in.close();
            out.close();
            socket.close();


        }

        catch(Exception e){
            e.printStackTrace();}
    }

    public String receive(){

        try{
            Socket socket=new Socket("192.168.43.53",2001);
            DataInputStream din=new DataInputStream(socket.getInputStream());
            String str = din.readUTF();//in.readLine();
            System.out.println("Message: "+str);
            din.close();
            socket.close();
            return str;

        }
        catch(Exception e){
            e.printStackTrace();
            return "Error";
        }




    }

}

