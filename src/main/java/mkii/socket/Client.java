package mkii.socket;


import mkii.rpc.entity.Request;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8888);

        // 需要远程调用的对象信息
        Request request = new Request();
        request.setClassName("mkii.socket.RemoteClass");
        request.setMethodName("method2");
        request.setParamTypes(new Class[]{String.class});
        request.setParameters(new Object[]{"hihi"});

        // 通过socket流发送信息到server
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();

        // 取得结果
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        String s = (String) objectInputStream.readObject();
        System.out.println("remote process call result: " + s);

        objectOutputStream.close();
        objectInputStream.close();
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
