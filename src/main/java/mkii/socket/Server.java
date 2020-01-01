package mkii.socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server  {
    public static void main(String[] args) throws Exception {
        //创建一个服务器端socket，指定绑定的端口号，并监听此端口
        ServerSocket serverSocket = new ServerSocket(8888);

        // 启动一个线程，用于接收消息
        ServerReceiveThread serverReceiveThread = new ServerReceiveThread("server", serverSocket);
        serverReceiveThread.start();
        System.out.println("server is ready...");

        OutputStream outputStream = null;
        Socket socket = null;

        while (true) {
            socket = serverSocket.accept();
            outputStream = socket.getOutputStream();

            // 从键盘读取
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            //向客户端传递的信息
            outputStream.write(message.getBytes());
            outputStream.flush();
            socket.shutdownOutput();
            if ("exit".equals(message)){
                break;
            }
        }

        //关闭资源
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}
