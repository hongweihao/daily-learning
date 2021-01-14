package mkii.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
    public static void main(String[] args) throws Exception {
        //创建一个服务器端socket，指定绑定的端口号，并监听此端口
        ServerSocket serverSocket = new ServerSocket(8888);

        Socket socket = null;

        while (true) {
            socket = serverSocket.accept();
            ServerReceiveThread serverReceiveThread = new ServerReceiveThread(socket);
            serverReceiveThread.start();
        }

        //关闭资源
        //socket.close();
        //serverSocket.close();
    }
}
