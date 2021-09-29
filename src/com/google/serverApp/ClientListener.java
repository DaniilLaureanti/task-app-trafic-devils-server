package com.google.serverApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;

public class ClientListener implements Runnable{

    private final Socket clientSocket;
    public static final String CMD_GET_RANDOM_BOOL = "get";
    private PrintWriter printWriter;
    private String command;

    public ClientListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.printWriter = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            assert scanner != null;
            if (!scanner.hasNextLine()) {
                break;
            }
            command = scanner.nextLine();
            System.out.println("Command from client: " + command);
            commandProcessor();
        }
    }

    public boolean getRandom(){
        int a = (int) ( Math.random() * 2 );
        return a == 1;
    }

    private void commandProcessor(){
        if (command.equalsIgnoreCase(CMD_GET_RANDOM_BOOL)){
            String message = String.valueOf(getRandom());
            sendToClient(message);
            System.out.println("Send to client: " + message);
        }else {
            System.out.println("Unknown client command");
        }
    }

    private void sendToClient(String message){
        printWriter.println(message);
        printWriter.flush();
    }
}
