package com.example;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Charles on 16. 2. 21..
 */
public class EchoClient {
    public static int PORT_NUM = EchoServer.PORT_NUM;

    public static void main(String[] args){
        System.out.println("Try to connect");
        try {
            //127.0.0.1 = loop back = localhost(jsp) = my Ip
            //connect after making a socket
            Socket socket = new Socket("127.0.0.1",PORT_NUM);
            System.out.println("connected");

            String msg = "";
            //msg가 0이면 프로그램 종료
            while(!msg.equals("0")){
                Scanner scanner = new Scanner(System.in);
                msg = scanner.next();
                socket.getOutputStream().write(msg.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished:Socket Close");
    }
}
