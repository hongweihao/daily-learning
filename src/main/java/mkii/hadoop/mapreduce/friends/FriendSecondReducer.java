package mkii.hadoop.mapreduce.friends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class FriendSecondReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        if (values == null){
            return;
        }
        StringBuilder builder = new StringBuilder();

        for (Text value : values) {
            String friend = value.toString();
            builder.append(friend);
            builder.append(',');
        }
        context.write(key, new Text(builder.toString()));
    }
}
