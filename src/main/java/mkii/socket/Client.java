package mkii.socket;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws Exception {
        ClientReceiveThread clientReceiveThread = new ClientReceiveThread("client", null);
        clientReceiveThread.start();
        System.out.println("client is ready...");

        Socket socket = null;
        OutputStream outputStream = null;
        while (true) {
            socket = new Socket("localhost", 8888);
            outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
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
    }
}
