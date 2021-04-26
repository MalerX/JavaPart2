package nio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NeoBASH implements Runnable {
    private static final int BUFFER_SIZE = 256;
    private final SocketChannel socketChannel;
    private static final Path INIT_PATH = Paths.get("server/serverFiles").toAbsolutePath();
    private final HandlerCommand command = new FirstOrder(INIT_PATH);

    public NeoBASH(SocketChannel channel) {
        this.socketChannel = channel;
        send("Welcome to the NeoBASH\n" +
                INIT_PATH + " >> ");
    }

    private void send(String s) {
        try {
            socketChannel.write(ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            System.err.println("Fail send: " + e);
        }
    }

    private String readFromChannel() {
        StringBuilder s = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            int read = socketChannel.read(buffer);
            while (read != 0) {
                buffer.flip();
                while (buffer.hasRemaining())
                    s.append((char) buffer.get());
                read = socketChannel.read(buffer);
            }
        } catch (IOException e) {
            System.err.println("Fail read: " + e);
        }
        return s.toString();
    }

    @Override
    public void run() {
        while (socketChannel.isOpen()) {
            String inStr = readFromChannel();
            if (!inStr.isEmpty()) {
                String result = command.execute(inStr);
                if (result.equals("close NeoBASH")) {
                    close();
                } else
                    send(result);
            }
        }
    }

    private void close() {
        try {
            send("Good Bay!\n");
            System.out.printf("Client from %s disconnect\n", socketChannel.getRemoteAddress());
            socketChannel.close();
        } catch (IOException e) {
            System.err.println("Fail close socket: " + e);
        }
    }
}
