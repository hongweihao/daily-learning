package mkii.hadoop.mapreduce.join.in.map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 根据提供的商品表t_product和订单表t_order，在map端实现以下的语句：
 * select a.id,a.date,b.name,b.category_id,b.price from t_order a join t_product b on a.pid = b.id
 *
 * 注意：
 * 1.bean类的序列化与反序列化问题
 * 2.从缓存中得到hdfs中的文件名，再利用流读取hdfs中的文件
 *
 */
public class JoinRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        Job job = Job.getInstance(conf);
        job.setJarByClass(mkii.hadoop.mapreduce.join.in.map.JoinRunner.class);

        job.setMapperClass(JoinMapper.class);

        // 设置reduce任务数为0。因为在map端完成join操作
        job.setNumReduceTasks(0);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 先把小表的文件加入缓存中。实际上这里只是将小表的文件名放入缓存，真正读取文件内容是在初始化mapper时调用setup()方法
        job.addCacheFile(new Path("/join/reduce/input/t_product").toUri());

        FileInputFormat.setInputPaths(job, new Path("/join/reduce/input/t_order"));
        FileOutputFormat.setOutputPath(job, new Path("/join/map/output"));

        job.waitForCompletion(true);
    }
}
