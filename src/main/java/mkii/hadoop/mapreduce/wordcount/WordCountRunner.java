package mkii.hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 打包：
 * 使用maven打包整个项目
 * mvn package
 *
 * 运行：
 * hadoop jar jar路径 主类全名
 * e.g: hadoop jar daily-learning-1.0-SNAPSHOT.jar mkii.hadoop.mapreduce.wordcount.WordCountRunner
 *
 */
public class WordCountRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        // 新建一个job
        Job job = Job.getInstance(conf);

        // 设置运行的基本包路径
        job.setJarByClass(WordCountRunner.class);

        // 设置map和reduce对应的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 设置输入文件路径和输出文件路径，这里也可以从命令行参数获取设置
        FileInputFormat.setInputPaths(job, new Path("/wordcount/input"));
        FileOutputFormat.setOutputPath(job, new Path("/wordcount/out"));

        // 提交job，并打印过程
        job.waitForCompletion(true);
    }
}
