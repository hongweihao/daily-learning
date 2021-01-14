package mkii.socket.nio;


import mkii.socket.entity.Request;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws Exception {
        // NIO借助通道channel。打开socket的channel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 9999));

        // 需要远程调用的对象信息
        Request request = new Request();
        request.setClassName("mkii.socket.RemoteClass");
        request.setMethodName("method2");
        request.setParamTypes(new Class[]{String.class});
        request.setParameters(new Object[]{"halo"});

        // 发送request的信息到server
        byte[] bytes = ConvertUtil.object2Bytes(request);
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        socketChannel.write(byteBuffer);

        // 取得结果
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        bytes = byteBuffer.array();
        String s = (String) ConvertUtil.bytes2Object(bytes);
        System.out.println(s);

        // nio读取文件并发送到channel
        /*String fileName = "d:/nio.txt";
        FileChannel fileChannel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);
        fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        fileChannel.close();*/

        socketChannel.close();
    }

}
