package mkii.socket.nio;

import lombok.SneakyThrows;
import mkii.rpc.entity.Request;
import mkii.socket.Handler;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioServerHandler implements Runnable {

    private SocketChannel socketChannel;

    public NioServerHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @SneakyThrows
    @Override
    public void run() {

        // 接收client的数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);

        /*// 这种方式将byteBuffer转为byte[]会导致stream header error
        int len = byteBuffer.limit() - byteBuffer.position();
        System.out.println(len);
        byte[] bytes = new byte[len];
        byteBuffer.get(bytes, 0, bytes.length);*/

        byte[] bytes = byteBuffer.array();
        Request request = (Request) ConvertUtil.bytes2Object(bytes);
        System.out.println(request.getClassName() + "->" + request.getMethodName());

        // 利用反射机制调用方法
        Handler handler = new Handler();
        Object o = handler.serverHandler(request);

        // 把方法调用的结果返回给client
        byteBuffer.clear();
        bytes = ConvertUtil.object2Bytes(o);
        byteBuffer = ByteBuffer.wrap(bytes);
        socketChannel.write(byteBuffer);

        socketChannel.close();
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setClassName("mkii.socket.RemoteClass");
        request.setMethodName("method2");
        request.setParamTypes(new Class[]{String.class});
        request.setParameters(new Object[]{"hihi"});

        /*byte[] bytes = ConvertUtil.object2Bytes(request);
        System.out.println(bytes.length);
        Request request1 = (Request) ConvertUtil.bytes2Object(bytes);
        System.out.println(request1.getClassName());*/

        ByteBuffer byteBuffer = ConvertUtil.Object2ByteBuffer(request);
        System.out.println(byteBuffer.toString());
        Request request1 = (Request) ConvertUtil.byteBuffer2Object(byteBuffer);
        System.out.println(request1.getClassName() + " -> " + request1.getMethodName());

    }
}
