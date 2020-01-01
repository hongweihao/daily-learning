package mkii.socket;

import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiveThread extends Thread {
    private String threadName;
    //private Socket socket;

    public ClientReceiveThread(String threadName, Socket socket) {
        this.threadName = threadName;
        //this.socket = socket;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            //获取服务器端传递的数据
            Socket socket = new Socket("localhost", 8888);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while ((info = bufferedReader.readLine()) != null) {
                /*if ("exit".equals(info)){
                    return;
                }*/
                System.out.println(threadName + " receive: " + info);
            }
        }
    }
}
