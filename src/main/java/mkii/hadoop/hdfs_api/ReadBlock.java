package mkii.hadoop.hdfs_api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;

/**
 * 模拟实现：获取一个文件的所有block位置信息，然后读取指定block中的内容
 *
 */
public class ReadBlock {
    FileSystem fileSystem;

    public ReadBlock(String defaultFs) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", defaultFs);
        fileSystem = FileSystem.get(conf);
    }

    public void readBlockList(String path) throws IOException {

        // 输入流，读hdfs的文件
        FSDataInputStream fsdInputStream = fileSystem.open(new Path(path));

        // 获取文件状态
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(path));

        // 得到文件的block信息    因为是测试环境，只有一个block
        BlockLocation[] fileBlockLocations = fileSystem.getFileBlockLocations(fileStatuses[0], 0, fileStatuses[0].getLen());

        // 计算block长度和起始偏移量
        int length = (int) fileBlockLocations[0].getLength();
        int offset = (int) fileBlockLocations[0].getOffset();

        System.out.println(length);
        System.out.println(offset);

        byte[] bytes = new byte[(length - offset)];
        fsdInputStream.read(bytes, offset, length);

        System.out.println(new String(bytes));
    }

    public static void main(String[] args) throws IOException {
        ReadBlock readBlock = new ReadBlock("hdfs://localhost:9000");
        readBlock.readBlockList("/hadoop_shell/nio.txt");
    }
}
