package mkii.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// 动态代理
public class IoType {

    public void bioRead() throws IOException, ClassNotFoundException {

        InputStream inputStream = new FileInputStream("D:\\tempfile");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        User newUser = (User) objectInputStream.readObject();
        System.out.println(newUser.getName() + ":" + newUser.getAge());

        objectInputStream.close();
        inputStream.close();
    }

    public void bioWrite() throws IOException{

        OutputStream outputStream = new FileOutputStream("D:\\tempfile");

        // User 需要实现Serializable 接口，否则会抛出NotSerializableException
        User user = new User();
        user.setName("hong");
        user.setAge(12);
        // 字节流,面向字节（byte[] 等）
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();

        // 字符流（char[], String 等）
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("44343424234");
        outputStreamWriter.close();

        outputStream.close();

        System.out.println("bioWrite");
    }

    public void nioWrite() throws IOException{

        String filePath = "D://nio.txt";

        // 1.构建文件输出流对象
        File tofile = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(tofile);

        // 2.构建通道
        FileChannel channel = fileOutputStream.getChannel();

        // 3.构建一个buffer区用于临时存储写入文件的内容
        // 通道不能脱离buffer工作
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode("同步非阻塞方式写入文件");
        int len = 0;

        // 通过通道将buffer中的内容写入文件
        while ((len = channel.write(byteBuffer)) > 0){
            System.out.println("wrote len:" + len);
        }

        byteBuffer.clear();
        channel.close();
        fileOutputStream.close();
    }

    public void nioRead() throws IOException{

        String filePath = "D://nio.txt";

        // 1.构建文件输入流对象fileInputStream
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        // 2.构建文件通道
        FileChannel channel = fileInputStream.getChannel();

        // 3.构建一个buffer区用于读取内容
        // utf8中文占用3个字符，如果给定capacity的时候，中文字符串被截断会发生乱码
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        int len = 0;

        // 4.通过通道读取文件内容，并放入buffer中
        while ((len = channel.read(byteBuffer)) != -1){
            byteBuffer.clear();
            byte[] bytes = byteBuffer.array();

            System.out.write(bytes, 0, len);
        }
        System.out.println();

        channel.close();
        fileInputStream.close();

    }

    public void aioRead() throws IOException, ExecutionException, InterruptedException {

        String filepath = "d:\\aio.txt";

        // 1.构建同步非阻塞通道AsynchronousFileChannel
        Path path = Paths.get(filepath);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);

        // 2.构建buffer存放读取的内容
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 3.通过通道读取文件内容，并存储在buffer中
        Future<Integer> result = channel.read(buffer, 0);

        // 猜测相当于路障，等待线程执行完毕再继续往下。。
        while (!result.isDone());

        /*
         * buffer中的flip涉及到capacity、position、limit三个概念
         * capacity：读写模式下都是相同的，代表容量
         * position：类似于读写指针，表示当前读/写到什么位置
         * limit：在写模式下，能写入多少数据，最大为capacity；在读模式下能读取多少数据，与缓存中的实际大小相同（相当于线性表中的size概念）
         *
         * flip()方法：buffer有两种模式：读/写，调用flip方法实际上是模式转化，写模式下调用即切换到读模式。
         */
        buffer.flip();

        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);

        System.out.println("read:" + new String(bytes, StandardCharsets.UTF_8));
        buffer.clear();
        channel.close();
    }

    public void aioWrite() throws IOException{

        //String filepath = "d:\\aio.txt";

        Path path = Paths.get("d:", "write.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("AIO写入文件！".getBytes());
        byteBuffer.put("AIO写入文件！\n".getBytes());
        byteBuffer.put("AIO写入文件！".getBytes());
        byteBuffer.flip();

        // CompletionHandler 方式写入文件，结束后回调
        channel.write(byteBuffer, 0,byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("完成后会调用这个方法！");
            }
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("失败后会调用这个方法！");
            }
        });

        // Future 方式写入文件
        //Future<Integer> result = channel.write(byteBuffer, 0);
        //while (!result.isDone());

        System.out.println("aiowrite");

        channel.close();

    }

    public static void main(String[] args) throws Exception{

        IoType ioType = new IoType();

        ioType.aioWrite();

    }

}

/**
 * User model class
 * if User doesn't implement Serializable it will throws NotSerializableException
 *
 *
 */
class User implements Serializable{

    private String name;
    private int age;

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}