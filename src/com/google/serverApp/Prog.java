package com.google.serverApp;

public class Prog {

    public void start(){
        greeting();
        Server server = new Server(6788);
        Thread thread = new Thread(server);
        thread.start();
    }

    private void greeting(){
        String title = String.format("Server send \"true\" or \"false\" to client by query \"%s\"", ClientListener.CMD_GET_RANDOM_BOOL);
        System.out.println("******************************************************");
        System.out.println(title);
        System.out.println("******************************************************");
    }
}
