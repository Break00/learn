package com.jason.lee;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        SocketChannel channel = SocketChannel.open();
//        channel.write();
//        channel.read();
    }
}
