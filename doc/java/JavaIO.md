### Java IO

##### BIO：Block-IO，同步且阻塞
> `Block-IO`是一种同步且阻塞的通信模式。是一个比较传统的通信方式，模式简单，使用方便。但并发处理能力低，依赖网速。

##### NIO：Non-Block-IO，同步非阻塞
> `Java SE1.4`版以后，针对网络传输效能优化的新功能。

##### AIO：Asynchronous-IO，异步非阻塞
> 在`NIO`的基础上引入了新的异步通信概念，并提供了异步文件通道和异步套接字通道实现。

### 区别
>   `AIO`原来的`IO`有同样的作用和目的，他们之间重要的区别是数据打包和传输的方式。原来的`IO`以流的方式处理数据，而`NIO`以块的方式处理数据。
  
> 面向流的`IO`系统一次一个字节地处理数据。一个输入流产生一个字节的数据，一个输出流消费一个字节数据。  

> 面向块的`IO`以块的形式处理数据，每一个操作都在一步中产生或者消费一个块。按块处理数据比字节处理数据要快的多，但比流式缺少优雅和简单的编码特性。

### 适用场景
> `BIO`适用与连接数目比较小且固定的架构，对服务器资源要求高，并发局限于应用中，但程序直观简单易理解。  

> `NIO`适用于连接数据多且连接比较短的架构。比如聊天服务器，并发局限与应用，程序比较复杂。  

> `AIO`适用于连接数目多且比较长的架构。比如相册服务器，充分调用`OS`参与并发操作，程序复杂。

### java版本中的`IO`

> JDK1.4之前的IO没有缓冲区的概念，不支持正则表达式，支持的字符集编码有限等等。
> JDK1.4的时候引入了非阻塞IO，也就是NIO 1.0，但遍历目录很困难，不支持文件系统的非阻塞操作等等。
> 为了解决这些问题，JDK1.7的时候引入了新的NIO--NIO 2.0

### [实例代码](https://gitee.com/mkii/studyTree/blob/master/code/src/main/java/com/mkii/code/base/IoType.java)
```
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

        // User 需要实现Serializable 接口，否则会抛出NotSerializableException
        User user = new User();
        user.setName("mkii");
        user.setAge(12);

        OutputStream outputStream = new FileOutputStream("D:\\tempfile");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(user);

        objectOutputStream.close();
        outputStream.close();

        System.out.println("bioWrite");
    }

    public void nioWrite() throws IOException{

        String filePath = "D://nio.txt";

        File tofile = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(tofile);
        FileChannel channel = fileOutputStream.getChannel();

        // 通道不能脱离buffer工作
        ByteBuffer byteBuffer = Charset.forName("utf8").encode("同步非阻塞方式写入文件");
        int len = 0;

        while ((len = channel.write(byteBuffer)) > 0){
            System.out.println("wrote len:" + len);
        }

        byteBuffer.clear();
        channel.close();
        fileOutputStream.close();
    }

    public void nioRead() throws IOException{

        String filePath = "D://nio.txt";

        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();

        // utf8中文占用3个字符，如果给定capacity的时候，中文字符串被截断会发生乱码
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        int len = 0;

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

        Path path = Paths.get(filepath);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> result = channel.read(buffer, 0);

        // 猜测相当于路障，等待线程执行完毕再继续往下。。
        while (!result.isDone()){}

        /**
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
        byteBuffer.flip();

        // CompletionHandler 方式写入文件
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
        /*Future<Integer> result = channel.write(byteBuffer, 0);
        while (!result.isDone());*/

        System.out.println("aiowrite");

        channel.close();

    }

    public static void main(String[] args) throws Exception{

        IoType ioType = new IoType();
        ioType.aioWrite();
    }
}

/**
 * 如果不实现Serializable接口会t抛出NotSerializableException
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
```

### 参考
[漫话：如何给女朋友解释什么是BIO、NIO和AIO？](https://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247485960&idx=1&sn=83d418c498c2d6df102bd227c9e5c7ff&chksm=cef5f9bef98270a8e5cbd23280fb211362c7ff17dcf8894df71ba42692348c8f99e101a3c35d&scene=0&xtrack=1#rd) 
[Java：前程似锦的 NIO 2.0](https://mp.weixin.qq.com/s/pIsZ-KW5bLGcP_71ZUE_ww)  
