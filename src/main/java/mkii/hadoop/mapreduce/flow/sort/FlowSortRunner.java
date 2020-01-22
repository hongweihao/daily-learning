package mkii.hadoop.mapreduce.flow.sort;

import mkii.hadoop.mapreduce.flow.summary.FlowInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 对日志数据中的上下行流量信息汇总，并输出按照总流量倒序排序的结果
 *
 * solve：
 * MR程序在处理数据的过程中会对数据排序(map输出的kv对传输到reduce之前，会排序)，排序的依据是map输出的key
 * 所以，我们如果要实现自己需要的排序规则，则可以考虑将排序因素放到key中，让key实现接口：WritableComparable
 * 然后重写key的compareTo方法
 *
 * 增加需求：按手机号码分区
 *
 *
 *
 */
public class FlowSortRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowSortRunner.class);

        job.setMapperClass(FlowSortMapper.class);
        job.setPartitionerClass(FlowSortPartitioner.class);
        job.setReducerClass(FlowSortReducer.class);

        // 设置reduceTask为0时，mapper不会进行排序过程。而是直接输出
        // 设置reducerTask为4，会产生4个结果文件。如果文件是空的也会产生一个空的文件
        job.setNumReduceTasks(4);

        job.setOutputKeyClass(FlowInfo.class);
        // NullWritable是Writable的一个特殊类，实现方法为空实现，不从数据流中读数据，也不写入数据，只充当占位符
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path("/flowSummary/input"));
        FileOutputFormat.setOutputPath(job, new Path("/flowSummary/partition"));
        job.waitForCompletion(true);
    }
}
