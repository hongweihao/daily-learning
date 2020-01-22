package mkii.hadoop.mapreduce.friends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 求出哪些人两两之间有共同好友，及他俩的共同好友都有谁？
 *
 * A:B,C,D,F,E,O
 * B:A,C,E,K
 * C:F,A,D,I
 * D:A,E,F,L
 * E:B,C,D,M,L
 * F:A,B,C,D,E,O,M
 * G:A,C,D,E,F
 * H:A,C,D,E,O
 * I:A,O
 * J:B,O
 * K:A,C,D
 * L:D,E,F
 * M:E,F,G
 * O:A,H,I,J
 *
 * 第一步：（重点是第一个reduce）
 * 先找出共同好友的用户列表，例如 A-B,C-B,D-B -> map(B,ACD) ->反转reduce(A-B, A)(A-B, C)...
 *
 */
public class FriendFirstRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        Job job = Job.getInstance(conf);
        job.setJarByClass(FriendFirstRunner.class);

        job.setMapperClass(FriendFirstMapper.class);
        job.setReducerClass(FriendFirstReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path("/friend/input"));
        FileOutputFormat.setOutputPath(job, new Path("/friend/output1"));

        job.waitForCompletion(true);
    }
}
