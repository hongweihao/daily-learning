package mkii.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 *
 *
 *
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        Iterator<LongWritable> iterator = values.iterator();
        while (iterator.hasNext()){
            iterator.next();
            count++;
        }
        context.write(key, new LongWritable(count));
    }
}
