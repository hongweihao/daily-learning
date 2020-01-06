package mkii.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {

    public static void main(String[] args) throws Exception {

        // 客户端socket绑定端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int len;
            while ((len = socketChannel.read(byteBuffer)) != -1) {
                byte[] bytes = byteBuffer.array();
                String s = new String(bytes, 0, byteBuffer.position());
                System.out.println(socketChannel + " receive: " + s);
            }
        }
    }
}
