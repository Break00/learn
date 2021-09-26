package com.jason.lee.learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientA {

    public static void main(String[] args) {
        try {
            while (true) {
                Socket socket = new Socket("127.0.0.1", 8080);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw.println("ClientA Data");
                pw.flush();
                String line = is.readLine();
                System.out.println(line);
                pw.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
