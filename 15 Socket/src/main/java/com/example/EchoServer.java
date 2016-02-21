package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Charles on 16. 2. 21..
 */
public class EchoServer {

    //포트는 2바이트로 표현 약 65000, 0~2^16-1
    //0~1023 system reserved port , 권장하지 않는포트

    public static int BUFFER_SIZE = 10;

    public static void main(String[] args) {

        int PORT_NUM = Integer.parseInt(args[0]);

        System.out.println("Server Started :" + PORT_NUM);
        try {
            //make a socket with binding
            ServerSocket mServerSocket = new ServerSocket(PORT_NUM);
            //non-block, accept including listening
            Socket clientSocket = mServerSocket.accept();
            System.out.println("Client connected");


            System.out.println("made buffer");

            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int size = clientSocket.getInputStream().read(buffer);
                System.out.println("received size:" + size);

                System.out.print("binary:");
                for (byte b : buffer) {
                    System.out.print(b + " ");
                }
                System.out.println("");

                System.out.println("msg from client:" + new String(buffer));

                if (new String(buffer).contains("0")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }
}
