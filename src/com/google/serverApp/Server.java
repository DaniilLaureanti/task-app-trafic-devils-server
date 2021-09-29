package com.google.serverApp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private final int PORT;

    public Server(int PORT) {
        this.PORT = PORT;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Local host: " + InetAddress.getLocalHost().getHostAddress());
            String messageStart = String.format("port: %d", PORT);
            System.out.println(messageStart);
            System.out.println("server start");
            System.out.println();

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("new client connected");
                ClientListener clientListener = new ClientListener(socket);
                new Thread(clientListener).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
