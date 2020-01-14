package mkii.hadoop.hdfs_api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HadoopJavaApi {
    /* 日志系统的门面模式，当变更使用其他日志框架时，可以不用修改代码 */
    private final Logger logger = LoggerFactory.getLogger(HadoopJavaApi.class);
    private FileSystem fileSystem;

    public HadoopJavaApi() throws IOException {
        // 1. 创建配置类，并设置配置信息
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
        // 2. 得到文件系统的对象
        fileSystem = FileSystem.get(configuration);
    }

    public void createDir(String path) throws IOException {
        // 创建目录
        fileSystem.mkdirs(new Path(path));
    }

    public void upload(String src, String dest) throws IOException {
        // 上传文件到hdfs
        fileSystem.copyFromLocalFile(new Path(src), new Path(dest));
    }

    public void download(String src, String dest) throws IOException {
        // 下载文件到本地
        fileSystem.copyToLocalFile(new Path(src), new Path(dest));
    }

    public void delete(String path) throws IOException {
        // 删除文件
        fileSystem.deleteOnExit(new Path(path));
    }

    public void listFiles(String path) throws IOException {
        logger.info("file list");
        logger.debug("debug file list");
        // 查看目录内容
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path(path), true);
        while (locatedFileStatusRemoteIterator.hasNext()){
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println(next.getPath().getName());
        }
    }

    public static void main(String[] args) throws IOException {
        HadoopJavaApi hadoopJavaApi = new HadoopJavaApi();
        /*hadoopJavaApi.createDir("/hadoop_shell");
        hadoopJavaApi.upload("D:\\nio.txt", "/hadoop_shell");
        hadoopJavaApi.download("/hadoop_shell/nio.txt", "D:\\xml\\");*/
        hadoopJavaApi.listFiles("/hadoop_shell");
    }
}
