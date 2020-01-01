package mkii.socket;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceiveThread extends Thread {

    private String threadName;
    private ServerSocket serverSocket;

    public ServerReceiveThread(String threadName, ServerSocket serverSocket){
        this.threadName = threadName;
        this.serverSocket = serverSocket;

    }

    @SneakyThrows
    @Override
    public void run(){
        while (true) {
            //调用accept()方法开始监听，等待客户端的连接。这里会阻塞
            Socket socket = null;
            socket = serverSocket.accept();
            //获取输入流，并读取客户端信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while ((info = bufferedReader.readLine()) != null) {
                /*if ("exit".equals(info)){
                    return;
                }*/
                System.out.println(threadName + " received：" + info);
            }
        }
    }
}
