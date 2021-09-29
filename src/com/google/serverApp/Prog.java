package com.google.serverApp;

public class Prog {

    public void start(){
        greeting();
        Server server = new Server(6789);
        Thread thread = new Thread(server);
        thread.start();
    }

    private void greeting(){
        System.out.println("Server");
    }
}
