package com.example;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Charles on 16. 2. 21..
 */
public class Client {
    public static int PORT_NUM = Server.PORT_NUM;

    public static int BUFFER_SIZE = 1024;
    public static void main(String[] args){
        System.out.println("Try to connect");
        try {
            //127.0.0.1 = loop back = localhost(jsp) = my Ip
            //connect after making a socket
            Socket socket = new Socket("127.0.0.1",PORT_NUM);
            System.out.println("connected");
            //make a buffer to get some data
            byte[] buffer = new byte[BUFFER_SIZE];
            System.out.println("made buffer");
            //receive data, blocked-method
            socket.getInputStream().read(buffer);
            System.out.println("has received data");
            //check
            System.out.println(new String(buffer));


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished:Socket Close");
    }
}
