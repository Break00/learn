package com.jason.lee.learn;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author huanli9
 * @description
 * @date 2021/1/10 21:33
 */
public class ThreadTest {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread printThread = new Thread(new Print(in),"PrintThread" );
        printThread.start();
        int receive = 0;
        try {
            while ((receive= System.in.read())!=-1){
                out.write(receive);
            }
        } finally {
            out.close();
        }
    }

    static class Print implements Runnable{
        private PipedReader inReader;

        public Print(PipedReader inReader) {
            this.inReader = inReader;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive=inReader.read())!=-1){
                    System.out.println((char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
