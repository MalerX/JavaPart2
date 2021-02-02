package server;

import commands.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {
    private ServerSocket server;
    private Socket socket;
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public Server() {
        clients = new CopyOnWriteArrayList<>();
        authService = new SimpleAuthService();
        try {
            server = new ServerSocket(PORT);
            System.out.println("server started");

            while (true) {
                socket = server.accept();
                System.out.println("client connected" + socket.getRemoteSocketAddress());
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(ClientHandler sender, String msg) {
        if (msg.startsWith(Command.PRIVATE_MSG)) {
            String[] destination = msg.split("\\s");
            StringBuilder sb = new StringBuilder(msg);
            sb.delete(0, destination[0].length() + destination[1].length() + 2);
            boolean flag = false;
            for (ClientHandler client : clients) {
                if (client.getNickname().equals(destination[1])) {
                    if (!client.equals(sender)) {
                        privateMessage(sender, client, sb.toString());
                        flag = true;
                    }
                }
            }
            if (!flag)
                sender.sendMsg("Пользователь с таким никнеймом не зарегистрирован.");
        } else broadcastMsg(sender, msg);
    }


    private void privateMessage(ClientHandler sender, ClientHandler destination, String msg) {
        String message = String.format("[ %s ] : %s", sender.getNickname(), msg);
        sender.sendMsg(message);
        destination.sendMsg(message);
    }

    private void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("[ %s ] : %s", sender.getNickname(), msg);
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public AuthService getAuthService() {
        return authService;
    }
}
