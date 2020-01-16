package mkii.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

/**
 * mapper因该遵循Hadoop的规则
 * 泛型列表表示input和output的数据类型
 * LongWriteable: 相当于Java的Long类型，表示读取文件的当前行偏移量
 * Text: 相当于Java的String类型，表示当前行偏移量对应的那一行的内容
 * Text: 表示output数据的key
 * LongWritable: 表示output数据的key对应的数据
 *
 * 因为Java的序列化机制相对比较复杂，且针对Java的对象类型。
 * hadoop创建了一种新的序列化机制，并且为此提供了新的对象类型
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println(key + " : " + value);

        // 切分单词
        String[] values = StringUtils.split(value.toString(), ' ');

        // 写入上下文对象，其实就是一个map。
        for (String s : values) {
            context.write(new Text(s), new LongWritable(1));
        }
    }
}
