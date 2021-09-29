package com.google.serverApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener implements Runnable{

    private final Socket clientSocket;
    private final String COMMAND_GET_RANDOM_BOOL = "true_or_false";
    private PrintWriter printWriter;

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
            String command = scanner.nextLine();
            if (command.equals(COMMAND_GET_RANDOM_BOOL)){
                printWriter.println(true);
                printWriter.flush();
            }
        }
    }
}
