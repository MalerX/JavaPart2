package nio.server;

public class StarterServer {
    private static final int PORT = 8189;

    public static void main(String[] args) {
        new NioServer(PORT).connect();
    }
}
