package com.jason.lee.learn;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientB {

    public static void main(String[] args) {
        try {
            while (true) {
                Socket socket = new Socket("127.0.0.1", 8080);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println("ClientB Data");
                pw.flush();
                pw.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
