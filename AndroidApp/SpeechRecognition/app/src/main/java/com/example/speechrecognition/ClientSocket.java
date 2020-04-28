package com.example.speechrecognition;

import android.os.StrictMode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
    static  Socket s;

    public void run(String path){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Socket socket=new Socket("192.168.43.53",2004);

            File file = new File(path);
            long length = file.length();
            byte[] bytes = new byte[8000];
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();

            int count;
            while((count = in.read(bytes)) > 0){
                out.write(bytes, 0, count);
            }

            out.close();
            in.close();
            socket.close();
        }

        catch(Exception e){
            e.printStackTrace();}
    }
}

