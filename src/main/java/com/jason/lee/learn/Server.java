package com.jason.lee.learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                Socket socket = server.accept();
                BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = is.readLine();
                System.out.println("receive from client " + line);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println("receive data: " + line);
                pw.flush();
                pw.close();
                is.close();
            }
        } catch (Exception ex) {

        } finally {

        }
    }
}
