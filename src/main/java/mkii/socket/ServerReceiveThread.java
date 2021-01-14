package mkii.socket;

import lombok.SneakyThrows;
import mkii.socket.entity.Request;

import java.io.*;
import java.net.Socket;

public class ServerReceiveThread extends Thread {
    private Socket socket;

    public ServerReceiveThread(Socket socket){
        this.socket = socket;
    }

    @SneakyThrows
    @Override
    public void run(){
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();

        // 调用逻辑处理
        Handler handler = new Handler();
        Object o = handler.serverHandler((Request) object);

        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();

        objectOutputStream.close();
        objectInputStream.close();
        outputStream.close();
        inputStream.close();
    }
}
