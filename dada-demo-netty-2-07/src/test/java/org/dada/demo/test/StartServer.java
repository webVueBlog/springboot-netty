package org.dada.demo.test;

import org.dada.demo.netty.server.ServerSocket;


public class StartServer {

    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
        System.out.println("dada-demo-netty server start done. ");
    }

}
