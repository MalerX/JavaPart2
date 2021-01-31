package homework.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private Socket socket = null;
    private static final int PORT = 8189;
    private ServerSocket serverSocket = null;
    private DataOutputStream out;
    private DataInputStream in;
    private Scanner scanner;

    public EchoServer() {
        try {
            openConnection();
            workedServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void workedServer() throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(in.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String strToClient = scanner.nextLine();
                try {
                    out.writeUTF(strToClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void openConnection() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Сервер запущен, ожидаем подключения...");
        socket = serverSocket.accept();
        System.out.println("Клиент подключился");
    }

    public static void main(String[] args) {
        new EchoServer();
    }
}
