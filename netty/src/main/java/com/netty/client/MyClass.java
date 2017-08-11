package com.netty.client;

import java.io.IOException;
import java.net.Socket;

public class MyClass {
    public void connect(){
        try {
            Socket socket = new Socket("192.168.120.109",8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new MyClass().connect();
    }
}
