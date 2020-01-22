package mkii.hadoop.mapreduce.friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class FriendSecondMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (value == null){
            return;
        }

        String[] twins = StringUtils.split(value.toString(), '\t');
        if (twins == null || twins.length != 2){
            return;
        }

        context.write(new Text(twins[0]), new Text(twins[1]));
    }
}
