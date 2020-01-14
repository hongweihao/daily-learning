package mkii.hadoop.hdfs_api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通过流方式访问hdfs，相对来说是一种更底层的操作方式
 * 上层那些mapreduce   spark等运算框架，去hdfs中获取数据的时候，就是调的这种底层的api
 *
 */
public class HadoopJavaApiByStream {

    FileSystem fileSystem;

    public HadoopJavaApiByStream(String defaultFs) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", defaultFs);
        fileSystem = FileSystem.get(conf);
    }

    public void upload(String src, String dest) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(src);
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(dest));
        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, 1024);
        fileInputStream.close();
        fsDataOutputStream.close();
    }

    public void download(String src, String dest) throws IOException {
        // 创建一个输入流，让文件内容输入到流中
        FSDataInputStream fsdInputStream = fileSystem.open(new Path(src));

        // 输出流，输出到本地
        FileOutputStream fileOutputStream = new FileOutputStream(new File(dest));

        // 将输入流中的内容copy到输出流
        IOUtils.copyBytes(fsdInputStream, fileOutputStream, 1024);

        fsdInputStream.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        HadoopJavaApiByStream hadoopJavaApiByStream = new HadoopJavaApiByStream("hdfs://localhost:9000");
        //hadoopJavaApiByStream.download("/hadoop_shell/nio.txt", "d:\\xml\\nio.txt");
        hadoopJavaApiByStream.upload("d:\\xml\\fromval.txt", "/hadoop_shell/fromval.txt");

    }
}
