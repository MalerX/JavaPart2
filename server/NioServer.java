package nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    private final int PORT;

    private ServerSocketChannel serverChannel;
    private Selector selector;

    public NioServer(int port) {
        this.PORT = port;
    }

    private void worker() throws IOException {
        while (serverChannel.isOpen()) {
            selector.select();
            freeCashDesk();
        }
    }

    private void freeCashDesk() throws IOException {
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectionKeys.iterator();

        while (it.hasNext()) {
            SelectionKey key = it.next();
            if (key.isAcceptable())
                handleAccept();
        }
        it.remove();
    }


    public void connect() {
        try {
            serverChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverChannel.bind(new InetSocketAddress(PORT));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            worker();
        } catch (Exception e) {
            System.err.println("Server was broken: " + e);
        }
    }

    private void handleAccept() throws IOException {
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        System.out.printf("Client from %s connect\n", channel.getRemoteAddress());
        new Thread(new NeoBASH(channel)).start();
    }
}
