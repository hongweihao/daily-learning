package mkii.hadoop.mapreduce.flow.summary;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计每一个用户（手机号）所耗费的总上行流量、下行流量，总流量
 *
 * 不知道哪些数据是上行流量和下行流量，随便选两个吧
 */
public class FlowSummaryRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowSummaryRunner.class);
        job.setMapperClass(FlowSummaryMapper.class);
        job.setReducerClass(FlowSummaryReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job, new Path("/flowSummary/input"));
        FileOutputFormat.setOutputPath(job, new Path("/flowSummary/output"));
        job.waitForCompletion(true);
    }
}
