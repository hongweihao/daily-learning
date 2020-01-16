#### 搭建好hadoop环境（单机测试可以是伪分布式）
> 启动hdfs和yarn（我用的是hadoop2.7.7）

#### 依赖包
> commons-cli-1.2.jar
> hadoop-common-2.7.7.jar
> hadoop-mapreduce-client-core-2.7.7.jar

#### MapReduce程序结构
> 一个mapper类，一个reducer类，一个定义mr过程的runner类

##### WordCountMapper
```java
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;
import java.io.IOException;

/**
 * Mapper的使用要遵循hadoop的Map规范
 *
 * Hadoop需要在网络上传输大量的数据，java的序列化机制对于Hadoop来说太过冗余，所以Hadoop自己实现了一套序列化机制
 * 使用Hadoop的序列化机制需要使用Hadoop的封装类型，IntWritable/Text。。。否则还是使用java序列化的机制。
 *
 * Mapper<Object, Text, Text, IntWritable>
 * Object：读取文件的偏移行数，实际上应该是LongWritable对象
 * Text：文件这一行的内容
 * Text：map之后输出的结果key类型
 * IntWritable：map之后输出的结果value类型
 */
public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    /**
     * map方法，每读取一行都会调用这个方法
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // StringUtils是Hadoop的工具类，这里可以用来分割字符串，好处应该是可以避免NPE
        String[] words = StringUtils.split(value.toString(), '*');
        for (String word : words){
            Text text = new Text(word);
            // map输出
            context.write(text, new IntWritable(1));
        }
    }
}
```

##### WordCountReducer
```java
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 * reduce程序，mapper的output会分组，相同的key分为一组，value以Iterable<IntWritable>形式给reducer
 * 这里的逻辑是统计key的value的个数
 *
 * Reducer<Text, IntWritable, Text, IntWritable>
 *     Text：接收来自Mapper的output key数据
 *     IntWritable：接收来自Mapper的output value数据
 *     Text：输出数据key
 *     IntWritable: 输出数据value
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable value : values){
            count++;
        }
        // reduce输出
        context.write(key, new IntWritable(count));
    }
}
```
##### WordCountRunner
```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * 规定MapReduce的流程，设置input和output等
 *
 * 1. 编译： javac *.class
 * 2. 打包成jar：jar -cvf mywc.jar *.class
 * 3. 准备目录和文件，需要创建input文件夹和input文件，output的文件夹不需要创建
 * 4. 运行：hadoop jar mywc.jar
 */
public class WordCountRunner {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 设置运行的基本包路径
        job.setJarByClass(WordCountRunner.class);

        // 设置map和reduce对应的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置输出类型，应该是reduce的输出类型，也可以说是总的
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置map的输出类型，如果不设置就会根据上面的设置
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 指定程序的input和output的路径,注意包
        FileInputFormat.setInputPaths(job, new Path("/mywc/input"));
        FileOutputFormat.setOutputPath(job, new Path("/mywc/output"));

        // 提交集群运行， verbose表示是否打印过程
        job.waitForCompletion(true);
    }
}
```

#### 运行
> 1. 需要保证HDFS集群和Yarn集群成功启动    
> 2. 需要保证HDFS的/mywc/input下面有处理的文件    
> 3. 需要保证HDFS不存在/mywc/output文件夹



