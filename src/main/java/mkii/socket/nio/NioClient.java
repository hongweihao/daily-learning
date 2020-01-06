package mkii.socket.nio;

import mkii.rpc.entity.Request;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioClient {
    public static void main(String[] args) throws Exception {
        // NIO借助通道channel。打开socket的channel
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 9999));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 需要远程调用的对象信息
        Request request = new Request();
        request.setClassName("mkii.socket.RemoteClass");
        request.setMethodName("method2");
        request.setParamTypes(new Class[]{String.class});
        request.setParameters(new Object[]{"hihi"});

        String fileName = "d:/nio.txt";
        FileChannel fileChannel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);
        fileChannel.transferTo(0, fileChannel.size(), channel);
        fileChannel.close();
        channel.close();
    }
}
