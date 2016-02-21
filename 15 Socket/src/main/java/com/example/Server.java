package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //포트는 2바이트로 표현 약 65000, 0~2^16-1
    //0~1023 system reserved port , 권장하지 않는포트

    public static int PORT_NUM =1104;
    public static void main(String[] args){

        System.out.println("Server Started :"+PORT_NUM);
        try {
            //make a socket with binding
            ServerSocket mServerSocket = new ServerSocket(PORT_NUM);
            //non-block, accept including listening
            Socket clientSocket = mServerSocket.accept();
            System.out.println("Client connected");

            clientSocket.getOutputStream().write("Hello World".getBytes());
            System.out.println("has sent 'Hello World'");


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }
}
